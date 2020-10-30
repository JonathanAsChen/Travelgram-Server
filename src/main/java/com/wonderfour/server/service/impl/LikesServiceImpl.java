package com.wonderfour.server.service.impl;

import com.wonderfour.server.dao.LikesMapper;
import com.wonderfour.server.entity.Likes;
import com.wonderfour.server.entity.LikesExample;
import com.wonderfour.server.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/25/2020 8:27 PM
 */
@Service
public class LikesServiceImpl implements LikesService {

    @Autowired
    private LikesMapper likesMapper;

    @Override
    public Likes findByuserIdandPostId(Integer userId, String postId) {
        LikesExample likesExample = new LikesExample();
        likesExample.createCriteria().andUserIdEqualTo(userId).andPostIdEqualTo(postId);
        List<Likes> likesList = likesMapper.selectByExample(likesExample);
        return likesList.isEmpty() ? null : likesList.get(0);
    }

    @Override
    public void likePost(Integer userId, String postId) {
        Likes likes = findByuserIdandPostId(userId, postId);
        if (likes == null) {
            likes = new Likes();
            likes.setPostId(postId);
            likes.setUserId(userId);
            likesMapper.insert(likes);
        }

    }

    @Override
    public void delikePost(Integer userId, String postId) {
        LikesExample likesExample = new LikesExample();
        likesExample.createCriteria().andUserIdEqualTo(userId).andPostIdEqualTo(postId);
        likesMapper.deleteByExample(likesExample);
    }
}
