package com.ifindpal.v1.filter;

import cn.hutool.core.util.StrUtil;
import com.ifindpal.v1.entity.LoginUser;
import com.ifindpal.v1.entity.User;
import com.ifindpal.v1.utils.JwtUtils;
import com.ifindpal.v1.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ansel Zhong
 * @description:
 * @date 2023/11/1 5:44
 * @ProjectName ifindpal-v1.0
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //1. Get Token
        String token = httpServletRequest.getHeader("authorization");
        // Token can be null so need to check
        if (StrUtil.isBlank(token)) {
           // permit
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            // 因为相应回来还要走一遍过滤器，如果不return就会走接下来的程序
            return;
        }

        //2. Resolve Token
        String userId = null;
        try {
            Claims claims = JwtUtils.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Filter1: TOKEN 非法");
        }


        //3. Get User From Redis
        RedisUtils.get("login:"+userId);
        LoginUser ifLogin = (LoginUser) RedisUtils.get("login:"+userId);
        if (ifLogin == null) {
            throw new RuntimeException("Filter2: 用户未登录");
        }


        User user = ifLogin.getUser();
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(user,null,ifLogin.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //permit
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
