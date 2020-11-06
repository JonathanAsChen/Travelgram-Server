package com.wonderfour.server.service;

import com.wonderfour.server.entity.Comment;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/31/2020 3:11 PM
 */
public interface CommentService {

    List<Comment> getByPostId(String postId);

    Comment getById(String id);

    String commentPost(Comment comment);

}
