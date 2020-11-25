package com.wonderfour.server.service;

import com.wonderfour.server.entity.Favorite;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/25/2020 8:34 PM
 */
public interface FavoriteService {

    Favorite findByuserIdandPostId(Integer userId, String postId);

    void favoritePost(Integer userId, String postId);

    void defavoritePost(Integer userId, String postId);

    List<Favorite> getFavoritesList(Integer userId);
}
