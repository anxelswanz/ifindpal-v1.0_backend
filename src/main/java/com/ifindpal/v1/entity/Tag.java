package com.ifindpal.v1.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ansel
 * @since 2023-11-04
 */
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer tagId;

    private Integer parentId;

    private String tagName;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "Tag{" +
            "tagId=" + tagId +
            ", parentId=" + parentId +
            ", tagName=" + tagName +
        "}";
    }
}
