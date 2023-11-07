package com.ifindpal.v1.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ifindpal.v1.entity.Message;
import com.ifindpal.v1.entity.Post;
import com.ifindpal.v1.entity.Tag;
import com.ifindpal.v1.entity.User;
import com.ifindpal.v1.mapper.MessageMapper;
import com.ifindpal.v1.mapper.PostMapper;
import com.ifindpal.v1.mapper.TagMapper;
import com.ifindpal.v1.mapper.UserMapper;
import com.ifindpal.v1.service.IPostService;
import com.ifindpal.v1.service.IUserService;
import com.ifindpal.v1.vo.RespBean;
import com.ifindpal.v1.vo.RespBeanEnum;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
@RequestMapping("/post")
public class PostController {
    @Autowired
    private IPostService postService;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/createPost")
    public RespBean createPost(@RequestBody Post post) {
        /**
         * 校验是否超过三次
         */
        String postUserId = post.getPostUserId();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", postUserId);
        User user = userMapper.selectOne(userQueryWrapper);
        int  postCountPerDay= user.getPostCountPerDay();
        if ( postCountPerDay> 3) {
            return RespBean.error(RespBeanEnum.ERROR,"EXCEED3");
        }
        postCountPerDay = postCountPerDay + 1;
        user.setPostCountPerDay(postCountPerDay);
        userMapper.updateById(user);
        /**
         * 生成post
         */
        String postId = generateId();
        post.setPostId(postId);
        System.out.println(post);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        post.setCreateTime(format);
        /**
         * 查找themeId
         */
        QueryWrapper<Tag> tagQueryWrapper = new QueryWrapper<>();
        tagQueryWrapper.eq("tag_id",post.getTagId());
        Tag tag = tagMapper.selectOne(tagQueryWrapper);
        post.setThemeId(tag.getParentId());
        boolean save = postService.save(post);
        if (save) {
            return RespBean.success();
        }else {
            return RespBean.error(RespBeanEnum.ERROR);
        }
    }


    @GetMapping("/getAllPost")
    public RespBean getAllPost(String currentCity, Integer themeId){

        if (currentCity.contains("%20")) {
            currentCity  = currentCity.replaceAll("%20", " ");
        }

        LambdaQueryWrapper<Post> postLambdaQueryWrapper
                = new LambdaQueryWrapper<Post>().orderByDesc(Post::getCreateTime);
        postLambdaQueryWrapper.eq(Post::getCurrentCity,currentCity);
        postLambdaQueryWrapper.eq(Post::getThemeId, themeId);
        List<Post> list = postService.list(postLambdaQueryWrapper);

        return RespBean.success(list);
    }

    /**
     * 根据时间戳生成唯一id
     */
    public String generateId(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        String id = sdf.format(System.currentTimeMillis());
        return "post" +id ;
    }

    @GetMapping("/fuzzyQueryPost")
    public RespBean fuzzyQueryPost(String str){
        LambdaQueryWrapper<Post> postLambdaQueryWrapper = new LambdaQueryWrapper<>();
     //   postLambdaQueryWrapper.like(Post::getContact,str);
        postLambdaQueryWrapper.like(Post::getDescription,str).or()
                        .like(Post::getEventName, str).or()
                        .like(Post::getLocation, str).or()
                        .like(Post::getPostNickName, str);
        postLambdaQueryWrapper.orderByDesc(Post::getCreateTime);
        List<Post> list = postService.list(postLambdaQueryWrapper);
        System.out.println(list);
        return RespBean.success(list);
    }

    @GetMapping("/getPostByUserId")
    public RespBean getPostByUserId(String userId) {
        LambdaQueryWrapper<Post> postLambdaQueryWrapper = new LambdaQueryWrapper<>();
        postLambdaQueryWrapper.eq(Post::getPostUserId,userId);
        postLambdaQueryWrapper.orderByDesc(Post::getCreateTime);
        List<Post> list = postService.list(postLambdaQueryWrapper);
        return RespBean.success(list);
    }

    @GetMapping("/deletePostById")
    public RespBean deletePostById(String postId) {
        QueryWrapper<Post> postQueryWrapper = new QueryWrapper<>();
        postQueryWrapper.eq("post_id", postId);
        postService.remove(postQueryWrapper);
        return RespBean.success();
    }

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private MessageMapper messageMapper;

    @GetMapping("/getMyEvent")
    public RespBean getMyEvent(String userId) {
        LambdaQueryWrapper<Message> messageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        messageLambdaQueryWrapper.eq(Message::getSender, userId);
        /**
         * BUG修复 这里也要选择 ifAccepted == 1 的 message
         */
        messageLambdaQueryWrapper.eq(Message::getIfAccepted,1);
        messageLambdaQueryWrapper.orderByDesc(Message::getCreateTime);
        List<Message> messages = messageMapper.selectList(messageLambdaQueryWrapper);
        ArrayList<String> postIds = new ArrayList<>();
        /**
         * BUG修复 这里也需要查找自己的post
         */
        QueryWrapper<Post> postQueryWrapper = new QueryWrapper<>();
        postQueryWrapper.eq("post_user_id", userId);
        List<Post> myPosts = postMapper.selectList(postQueryWrapper);
        if (messages.size() > 0) {
            for (Message message : messages) {
                String postId = message.getPostId();
                postIds.add(postId);
            }
            List<Post> posts = postMapper.selectBatchIds(postIds);
            posts.addAll(myPosts);
            System.out.println(posts);
            return RespBean.success(posts);
        }else {
            return RespBean.success(myPosts);
        }
    }
}
