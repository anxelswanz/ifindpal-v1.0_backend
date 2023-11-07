package com.ifindpal.v1.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ifindpal.v1.entity.LoginUser;
import com.ifindpal.v1.entity.User;
import com.ifindpal.v1.mapper.UserMapper;
import com.ifindpal.v1.service.IUserService;
import com.ifindpal.v1.utils.JwtUtils;
import com.ifindpal.v1.utils.RedisUtils;
import com.ifindpal.v1.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ansel
 * @since 2023-10-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public RespBean login(User user) {
    // AthenticationManager conducts Authentication
    // method authenticate(Authentication authentication);
    // UsernamePasswordAuthenticationToken implements Authentication
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        // This method will call the method in UserDetail Service
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        // 1. Not Valid: Exception
        if (ObjectUtil.isNull(authenticate)) {
            throw new RuntimeException("Login Failure: Invalid User");
        }

        // 2. Valid: create a JWT
        LoginUser validUser = (LoginUser) authenticate.getPrincipal();
        User findUser = validUser.getUser();
        String userId = validUser.getUser().getUserId().toString();
        String jwt = JwtUtils.createJWT(userId);

        // 3. Store the userid into the redis as key
        RedisUtils.set("login:"+userId,validUser);

        //return key-value
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",jwt);
        findUser.setPassword(null);
        map.put("user",findUser);
        return RespBean.success(map);
    }

    @Override
    public RespBean logout() {
        UsernamePasswordAuthenticationToken authentication
                =(UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String userId = user.getUserId();
        RedisUtils.del("login:"+userId);
        return RespBean.success();
    }
}
