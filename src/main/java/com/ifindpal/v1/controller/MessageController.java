package com.ifindpal.v1.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ifindpal.v1.entity.Message;

import com.ifindpal.v1.entity.Pal;
import com.ifindpal.v1.entity.User;
import com.ifindpal.v1.mapper.MessageMapper;
import com.ifindpal.v1.mapper.PalMapper;
import com.ifindpal.v1.mapper.UserMapper;
import com.ifindpal.v1.service.IMessageService;
import com.ifindpal.v1.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ansel
 * @since 2023-11-05
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private IMessageService messageService;
    @PostMapping("/createMessage")
    public RespBean createMessage(@RequestBody Message message){
        String postId = message.getPostId();
        String sender = message.getSender();
        QueryWrapper<Message> messageQueryWrapper = new QueryWrapper<>();
        messageQueryWrapper.eq("sender", sender);
        messageQueryWrapper.eq("post_id",postId);
        Message one = messageMapper.selectOne(messageQueryWrapper);
        if (ObjectUtil.isNotEmpty(one)) {
            return RespBean.success("MESSAGENOTALLOWED");
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = sdf.format(date);
        message.setCreateTime(format);
        message.setIfAccepted(0);
        message.setMessageId("mes"+generateId());
        messageMapper.insert(message);
        return RespBean.success();
    }

    /**
     * 根据时间戳生成唯一id
     */
    public String generateId(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        String id = sdf.format(System.currentTimeMillis());
        return id;
    }

    @GetMapping("/getMessageByUserId")
    public RespBean GetMessageByUserId(String userId){
        LambdaQueryWrapper<Message> messageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        messageLambdaQueryWrapper.eq(Message::getReceiver, userId);
        messageLambdaQueryWrapper.eq(Message::getIfAccepted,0);
        messageLambdaQueryWrapper.orderByDesc(Message::getCreateTime);
        List<Message> messages = messageMapper.selectList(messageLambdaQueryWrapper);
        return RespBean.success(messages);
    }


    @Autowired
    private PalMapper palMapper;

    @PostMapping("/messageHandling")
    public RespBean messageHandling(@RequestBody Message message) {
        String messageId = message.getMessageId();
        Integer ifAccepted = message.getIfAccepted();
        QueryWrapper<Message> messageQueryWrapper = new QueryWrapper<>();
        messageQueryWrapper.eq("message_id", messageId);
        Message one = messageMapper.selectOne(messageQueryWrapper);
        one.setIfAccepted(ifAccepted);
        messageMapper.updateById(one);
        /**
         * BUG修复 如果没通过 则返回
         * 不进行配对
         */
        if (ifAccepted == 2) {
            return RespBean.success();
        }
        // pal 配对
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String matchId = generateId();
        String format = sdf.format(date);
        Pal pal1 = new Pal();
        pal1.setMatchId("A"+matchId);
        pal1.setPalTime(format);
        pal1.setIfShow(0);
        pal1.setUserId(one.getSender());
        pal1.setPalId(one.getReceiver());
        Pal pal2 = new Pal();
        pal2.setMatchId("B"+matchId);
        pal2.setUserId(one.getReceiver());
        pal2.setPalId(one.getSender());
        pal2.setPalTime(format);
        pal2.setIfShow(0);
        palMapper.insert(pal1);
        palMapper.insert(pal2);
        return RespBean.success();
    }

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getJoinUsers")
    public RespBean getJoinUsers(String postId) {
        LambdaQueryWrapper<Message> messageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        messageLambdaQueryWrapper.eq(Message::getPostId,postId);
        messageLambdaQueryWrapper.eq(Message::getIfAccepted, 1);
        messageLambdaQueryWrapper.orderByAsc(Message::getCreateTime);
        List<Message> list = messageMapper.selectList(messageLambdaQueryWrapper);
        ArrayList<String> userIds = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(list)) {
            for (Message message : list) {
                String senderId = message.getSender();
                userIds.add(senderId);
            }
            List<User> users = userMapper.selectBatchIds(userIds);
            ArrayList<User> newList = new ArrayList<>();
            for (User user : users) {
                user.setPassword(null);
                user.setEmail(null);
                user.setUserName(null);
                newList.add(user);
            }
            return RespBean.success(newList);
        }
        return RespBean.success(list);
    }


    @PostMapping("/personalMsgHandling")
    public RespBean personalMsgHandling(@RequestBody Message message) {
        message.setIfAccepted(0);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String messageId = generateId();
        String format = sdf.format(date);
        message.setCreateTime(format);
        message.setMessageId("mes" + messageId);
        messageMapper.insert(message);
        return RespBean.success();
    }

    @GetMapping("/getPersonalMsg")
    public RespBean getPersonalMsg(String userId){
        LambdaQueryWrapper<Message> messageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        messageLambdaQueryWrapper.eq(Message::getReceiver, userId);
        messageLambdaQueryWrapper.eq(Message::getIfAccepted,0);
        messageLambdaQueryWrapper.eq(Message::getType,"personal");
        messageLambdaQueryWrapper.orderByDesc(Message::getCreateTime);
        List<Message> messages = messageMapper.selectList(messageLambdaQueryWrapper);
        return RespBean.success(messages);
    }

    @GetMapping("/setMsgRead")
    public RespBean setMsgRead(String messageId){
        QueryWrapper<Message> messageQueryWrapper = new QueryWrapper<>();
        messageQueryWrapper.eq("message_id", messageId);
        Message message = messageMapper.selectOne(messageQueryWrapper);
        message.setIfAccepted(1);
        messageMapper.updateById(message);
        return RespBean.success();
    }
}
