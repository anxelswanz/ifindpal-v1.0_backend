package com.ifindpal.v1.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ansel Zhong
 * @description:
 * @date 2023/10/31 19:09
 * @ProjectName ifindpal-v1.0
 **/
@Data
@AllArgsConstructor
public class LoginUser implements UserDetails {

    private User user;

    private List<String> permisisions;

    /**
     * 不序列化这个成员变量
     */
    @JSONField(serialize = false)
    private  List<SimpleGrantedAuthority> authorities;

//    public LoginUser(User user, List<String> permisisions) {
//        this.user = user;
//        this.permisisions = permisisions;
//    }
    public LoginUser(User user) {
        this.user = user;
    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        /*
//            optimize: only the first time execute the codes after
//         */
////        if (authorities != null) {
////            return authorities;
////        }
////        authorities
////                = permisisions.stream()
////                    .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
////        return authorities;
//    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
