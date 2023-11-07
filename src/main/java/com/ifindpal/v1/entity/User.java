package com.ifindpal.v1.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author ansel
 * @since 2023-10-31
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String userId;

    private String userName;

    private String email;

    private Integer gender;

    private String currentCity;

    private String selfDescription;

    private String nickName;

    private String avatar;

    private String password;

    private String createTime;

    private int ifActivate;

    private int status;

    private int postCountPerDay;



    public int getPostCountPerDay() {
        return postCountPerDay;
    }

    public void setPostCountPerDay(int postCountPerDay) {
        this.postCountPerDay = postCountPerDay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIfActivate() {
        return ifActivate;
    }

    public void setIfActivate(int ifActivate) {
        this.ifActivate = ifActivate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }
    public String getSelfDescription() {
        return selfDescription;
    }

    public void setSelfDescription(String selfDescription) {
        this.selfDescription = selfDescription;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", currentCity='" + currentCity + '\'' +
                ", selfDescription='" + selfDescription + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                ", createTime='" + createTime + '\'' +
                ", ifActivate=" + ifActivate +
                ", status=" + status +
                ", postCountPerDay=" + postCountPerDay +
                '}';
    }
}
