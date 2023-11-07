package com.ifindpal.v1.entity;

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
public class Pal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String matchId;
    private String userId;

    private String palId;

    private String palTime;

    /**
     * 0: 第一次 1:已出现
     */
    private Integer ifShow;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPalId() {
        return palId;
    }

    public void setPalId(String palId) {
        this.palId = palId;
    }
    public String getPalTime() {
        return palTime;
    }

    public void setPalTime(String palTime) {
        this.palTime = palTime;
    }
    public Integer getIfShow() {
        return ifShow;
    }

    public void setIfShow(Integer ifShow) {
        this.ifShow = ifShow;
    }

    @Override
    public String toString() {
        return "Pal{" +
                "matchId='" + matchId + '\'' +
                ", userId='" + userId + '\'' +
                ", palId='" + palId + '\'' +
                ", palTime='" + palTime + '\'' +
                ", ifShow=" + ifShow +
                '}';
    }
}
