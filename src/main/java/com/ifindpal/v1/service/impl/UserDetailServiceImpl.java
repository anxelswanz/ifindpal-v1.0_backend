package com.ifindpal.v1.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ifindpal.v1.entity.LoginUser;
import com.ifindpal.v1.entity.User;
import com.ifindpal.v1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ansel Zhong
 * @description:
 * @date 2023/10/31 19:32
 * @ProjectName ifindpal-v1.0
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name",s);
        User user = userMapper.selectOne(userQueryWrapper);
        if (ObjectUtil.isEmpty(user)) {
            throw new RuntimeException("No Such User");
        }
        //TODO QUERY Authorization
        //List<String> list = Arrays.asList("test", "admin");
        return new LoginUser(user);
    }
}
