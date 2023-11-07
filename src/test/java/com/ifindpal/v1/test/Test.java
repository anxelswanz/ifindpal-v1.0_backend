package com.ifindpal.v1.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Ansel Zhong
 * @description:
 * @date 2023/10/31 19:54
 * @ProjectName ifindpal-v1.0
 **/
@SpringBootTest
public class Test {

    @Autowired
    PasswordEncoder passwordEncoder;

//    @Test
//    public void testPasswordEncode(){
//        BCryptPasswordEncoder be = new BCryptPasswordEncoder();
//        String encode = passwordEncoder.encode("123");
//        System.out.println(encode);
//        System.out.println(passwordEncoder.matches("123", encode));
//    }
}
