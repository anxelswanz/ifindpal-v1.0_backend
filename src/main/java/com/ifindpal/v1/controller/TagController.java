package com.ifindpal.v1.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ifindpal.v1.entity.Post;
import com.ifindpal.v1.entity.Tag;
import com.ifindpal.v1.mapper.PostMapper;
import com.ifindpal.v1.mapper.TagMapper;
import com.ifindpal.v1.service.IPostService;
import com.ifindpal.v1.vo.RespBean;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ansel Zhong
 * @description:
 * @date 2023/11/4 5:47
 * @ProjectName ifindpal-v1.0
 **/
@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private IPostService postService;

    @GetMapping("/getTagsByTheme")
    public RespBean getTagsByTheme(Integer themeId) {
        QueryWrapper<Tag> tagQueryWrapper = new QueryWrapper<>();
        tagQueryWrapper.eq("parent_id", themeId);
        List<Tag> tags = tagMapper.selectList(tagQueryWrapper);
        return RespBean.success(tags);
    }

    @GetMapping("/geContentsByTagId")
    public RespBean geContentsByTagId(Integer tagId, String currentCity) {
        if (currentCity.contains("%20")) {
            currentCity  = currentCity.replaceAll("%20", " ");
        }
        LambdaQueryWrapper<Post> postLambdaQueryWrapper
                = new LambdaQueryWrapper<Post>().orderByDesc(Post::getCreateTime);
        postLambdaQueryWrapper.eq(Post::getTagId,tagId);
        postLambdaQueryWrapper.eq(Post::getCurrentCity,currentCity);
        List<Post> list = postService.list(postLambdaQueryWrapper);
        return RespBean.success(list);
    }
}
