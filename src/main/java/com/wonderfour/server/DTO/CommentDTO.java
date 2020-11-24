package com.wonderfour.server.DTO;

import lombok.Data;

import java.util.Date;

/**
 * @author Yifan Chen Hongyu Su
 * @version 1.0.0
 * @since 10/31/2020 3:08 PM
 */
@Data
public class CommentDTO {

    String id;

    private UserProfileDTO user;

    private String postId;

    private String content;

    private Date createTime;
}
