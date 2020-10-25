package com.wonderfour.server.service.impl;

import com.wonderfour.server.dao.FavoriteMapper;
import com.wonderfour.server.dao.LikesMapper;
import com.wonderfour.server.dao.PostMapper;
import com.wonderfour.server.entity.*;
import com.wonderfour.server.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private LikesMapper likesMapper;

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private PostServiceImpl postService;

    @Override
    public List<Post> recommendByLikes() {
        LikesExample example = new LikesExample();
        List<Likes> likesList = likesMapper.selectByExample(example);
        Map<String, Integer> countMap = new HashMap<>();
        for (Likes like : likesList) {
            countMap.put(like.getPostId(), countMap.getOrDefault(like.getPostId(), 0) + 1);
        }
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(countMap.entrySet());
        Collections.sort(entryList, (l1, l2) -> l2.getValue() - l1.getValue());
        List<Post> topPosts = new ArrayList<>();
        for (int i = 0; i < entryList.size() && i < 10; i++) {
            topPosts.add(postService.findById(entryList.get(i).getKey()));
        }
        return topPosts;
    }

    @Override
    public List<Post> recommendByFavorites() {
        FavoriteExample example = new FavoriteExample();
        List<Favorite> favoritesList = favoriteMapper.selectByExample(example);
        Map<String, Integer> countMap = new HashMap<>();
        for (Favorite favorite : favoritesList) {
            countMap.put(favorite.getPostId(), countMap.getOrDefault(favorite.getPostId(), 0) + 1);
        }
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(countMap.entrySet());
        Collections.sort(entryList, (l1, l2) -> l2.getValue() - l1.getValue());
        List<Post> topPosts = new ArrayList<>();
        for (int i = 0; i < entryList.size() && i < 10; i++) {
            topPosts.add(postService.findById(entryList.get(i).getKey()));
        }
        return topPosts;
    }
}
