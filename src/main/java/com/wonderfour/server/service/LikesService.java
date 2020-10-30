package com.wonderfour.server.service;

import com.wonderfour.server.entity.Likes;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/25/2020 8:25 PM
 */
public interface LikesService {

    Likes findByuserIdandPostId(Integer userId, String postId);

    void likePost(Integer userId, String postId);

    void delikePost(Integer userId, String postId);
}
