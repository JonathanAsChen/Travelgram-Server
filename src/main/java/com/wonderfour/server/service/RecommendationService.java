package com.wonderfour.server.service;

import com.wonderfour.server.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecommendationService {

    List<Post> recommendByLikes();

    List<Post> recommendByFavorites();
}
