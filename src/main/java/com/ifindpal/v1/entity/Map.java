package com.ifindpal.v1.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ansel
 * @since 2023-11-06
 */
public class Map implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String userId;

    private String avatar;

    private String lat;

    private String lng;

    private String currentCity;

    @TableField(exist = false)
    private boolean ifShow;

    public boolean getIfShow() {
        return ifShow;
    }

    public void setIfShow(boolean ifShow) {
        this.ifShow = ifShow;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    @Override
    public String toString() {
        return "Map{" +
                "userId='" + userId + '\'' +
                ", avatar='" + avatar + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", currentCity='" + currentCity + '\'' +
                ", ifShow=" + ifShow +
                '}';
    }
}
