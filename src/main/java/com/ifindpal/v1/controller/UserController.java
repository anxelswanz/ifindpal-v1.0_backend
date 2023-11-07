package com.ifindpal.v1.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ifindpal.v1.entity.User;
import com.ifindpal.v1.mapper.UserMapper;
import com.ifindpal.v1.service.IUserService;
import com.ifindpal.v1.vo.RespBean;
import com.ifindpal.v1.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ansel
 * @since 2023-10-31
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping ("/login")
    public RespBean login(@RequestBody User user){
        //Login
        return userService.login(user);
    }


    @RequestMapping("/logout")
    public RespBean logout(){
        return userService.logout();
    }


    @PostMapping("/createUser")
    public RespBean createUser(@RequestBody User user){
        //查看username是否被用过
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", user.getUserName());
        User one = userService.getOne(userQueryWrapper);
        if (ObjectUtil.isNotEmpty(one)) {
            return RespBean.error(RespBeanEnum.ERROR,"USERNAMEUSED");
        }
        System.out.println(user);
        //创建日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createDay = sdf.format(new Date());
        user.setCreateTime(createDay);
        user.setIfActivate(0);
        user.setStatus(1);
        String id = generateId();
        user.setUserId(id);
        //密码encode
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);

        int insert = userMapper.insert(user);
        if (insert != 0) {
            user.setPassword("");
            return RespBean.success(user);
        }else {
            return RespBean.error(RespBeanEnum.ERROR);
        }
    }

    @PostMapping("/oneMoreStep")
    public RespBean oneMoreStep(@RequestBody User reqUser) {
        System.out.println("req=>" +reqUser);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", reqUser.getUserId());
        User user = userMapper.selectOne(userQueryWrapper);
        user.setCurrentCity( reqUser.getCurrentCity());
        user.setAvatar(reqUser.getAvatar());
        user.setIfActivate(1);
        user.setNickName(reqUser.getNickName());
        userMapper.updateById(user);
        return RespBean.success(user);
    }


    /**
     * 获取user
     */
    @PostMapping("/updateUser")
    public RespBean updateUser(@RequestBody User user){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", user.getUserId());
        User update = userMapper.selectOne(userQueryWrapper);
        update.setNickName(user.getNickName());
        update.setEmail(user.getEmail());
        userMapper.updateById(update);
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

}
