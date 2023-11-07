package com.ifindpal.v1.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ansel
 * @since 2023-11-05
 */
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String postId;

    private String postUserId;

    private String eventName;

    private Integer tagId;

    private String description;

    private String contactWay;

    private String contact;

    private String eventDate;

    private String expireDate;

    private String location;

    private String postcode;

    private String postNickName;

    private String currentCity;

    private String postUserAvatar;

    private String createTime;

    private Integer themeId;

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPostUserAvatar() {
        return postUserAvatar;
    }

    public void setPostUserAvatar(String postUserAvatar) {
        this.postUserAvatar = postUserAvatar;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getPostNickName() {
        return postNickName;
    }

    public void setPostNickName(String postNickName) {
        this.postNickName = postNickName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
    public String getPostUserId() {
        return postUserId;
    }

    public void setPostUserId(String postUserId) {
        this.postUserId = postUserId;
    }
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", postUserId='" + postUserId + '\'' +
                ", eventName='" + eventName + '\'' +
                ", tagId=" + tagId +
                ", description='" + description + '\'' +
                ", contactWay='" + contactWay + '\'' +
                ", contact='" + contact + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", expireDate='" + expireDate + '\'' +
                ", location='" + location + '\'' +
                ", postcode='" + postcode + '\'' +
                ", postNickName='" + postNickName + '\'' +
                ", currentCity='" + currentCity + '\'' +
                ", postUserAvatar='" + postUserAvatar + '\'' +
                ", createTime='" + createTime + '\'' +
                ", themeId=" + themeId +
                '}';
    }
}
