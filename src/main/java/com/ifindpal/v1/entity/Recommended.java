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
public class Recommended implements Serializable {

    private static final long serialVersionUID = 1L;

    private String url;

    private String applyName;

    @TableId
    private Integer id;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Recommended{" +
            "url=" + url +
            ", applyName=" + applyName +
            ", id=" + id +
        "}";
    }
}
