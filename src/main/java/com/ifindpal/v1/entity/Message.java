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
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sender;

    private String receiver;

    /**
     * 0 未看 1 接收 2 拒绝
     */
    private Integer ifAccepted;


    @TableId
    private String messageId;

    private String postId;

    private String createTime;

    private String senderInfo;

    private String senderContact;

    private String senderAvatar;

    private String senderName;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSenderAvatar() {
        return senderAvatar;
    }

    public void setSenderAvatar(String senderAvatar) {
        this.senderAvatar = senderAvatar;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public Integer getIfAccepted() {
        return ifAccepted;
    }

    public void setIfAccepted(Integer ifAccepted) {
        this.ifAccepted = ifAccepted;
    }
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(String senderInfo) {
        this.senderInfo = senderInfo;
    }
    public String getSenderContact() {
        return senderContact;
    }

    public void setSenderContact(String senderContact) {
        this.senderContact = senderContact;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", ifAccepted=" + ifAccepted +
                ", messageId='" + messageId + '\'' +
                ", postId='" + postId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", senderInfo='" + senderInfo + '\'' +
                ", senderContact='" + senderContact + '\'' +
                ", senderAvatar='" + senderAvatar + '\'' +
                ", senderName='" + senderName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
