package com.wonderfour.server.entity;

import java.io.Serializable;

public class PostTag implements Serializable {
    private Integer ptId;

    private Integer postId;

    private Integer tagId;

    private static final long serialVersionUID = 1L;

    public Integer getPtId() {
        return ptId;
    }

    public void setPtId(Integer ptId) {
        this.ptId = ptId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ptId=").append(ptId);
        sb.append(", postId=").append(postId);
        sb.append(", tagId=").append(tagId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}