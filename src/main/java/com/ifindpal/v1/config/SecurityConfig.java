package com.ifindpal.v1.config;

import com.ifindpal.v1.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Ansel Zhong
 * @description:
 * @date 2023/10/31 19:55
 * @ProjectName ifindpal-v1.0
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/user/login").anonymous()
                .antMatchers("/user/createUser").permitAll()
                .antMatchers("/user/updateUser").permitAll()
                .antMatchers("/user/oneMoreStep").permitAll()
                .antMatchers("/post/getAllPost").permitAll()
                .antMatchers("/tag/geContentsByTagId").permitAll()
                .antMatchers("/tag/getTagsByTheme").permitAll()
                .antMatchers("/post.fuzzyQueryPost").permitAll()
                .antMatchers("/post/getPostByUserId").permitAll()
                .antMatchers("/post/deletePostById").permitAll()
                .antMatchers("/message/createMessage").permitAll()
                .antMatchers("/message/getMessageByUserId").permitAll()
                .antMatchers("/message/messageHandling").permitAll()
                .antMatchers("/message/getMyEvent").permitAll()
                .antMatchers("/message/getJoinUsers").permitAll()
                .antMatchers("/message/personalMsgHandling").permitAll()
                .antMatchers("/message/getPersonalMsg").permitAll()
                .antMatchers("/message/setMsgRead").permitAll()
                .antMatchers("/pal/getPals").permitAll()
                .antMatchers("/map/getUsersByCity").permitAll()
                .antMatchers("/map/setMeOnMap").permitAll()
                .antMatchers("/map/ifMeOnTheMap").permitAll()
                .antMatchers("/recommended/getRecommended").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        http
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
