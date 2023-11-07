package com.ifindpal.v1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ifindpal.v1.entity.Post;
import com.ifindpal.v1.mapper.PostMapper;
import com.ifindpal.v1.service.IPostService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ansel
 * @since 2023-11-05
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

}
