package com.wonderfour.server.service.impl;

import com.wonderfour.server.dao.FavoriteMapper;
import com.wonderfour.server.entity.Favorite;
import com.wonderfour.server.entity.FavoriteExample;
import com.wonderfour.server.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/25/2020 8:34 PM
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    public Favorite findByuserIdandPostId(Integer userId, String postId) {
        FavoriteExample favoriteExample = new FavoriteExample();
        favoriteExample.createCriteria().andUserIdEqualTo(userId).andPostIdEqualTo(postId);
        List<Favorite> likesList = favoriteMapper.selectByExample(favoriteExample);
        return likesList.isEmpty() ? null : likesList.get(0);
    }

    @Override
    public void favoritePost(Integer userId, String postId) {
        Favorite favorite = findByuserIdandPostId(userId, postId);
        if (favorite == null) {
            favorite = new Favorite();
            favorite.setPostId(postId);
            favorite.setUserId(userId);
            favoriteMapper.insert(favorite);
        }
    }

    @Override
    public void defavoritePost(Integer userId, String postId) {
        FavoriteExample favoriteExample = new FavoriteExample();
        favoriteExample.createCriteria().andUserIdEqualTo(userId).andPostIdEqualTo(postId);
        favoriteMapper.deleteByExample(favoriteExample);
    }

    @Override
    public List<Favorite> getFavoritesList(Integer userId) {
        FavoriteExample favoriteExample = new FavoriteExample();
        favoriteExample.createCriteria().andUserIdEqualTo(userId);
        return favoriteMapper.selectByExample(favoriteExample);
    }
}
