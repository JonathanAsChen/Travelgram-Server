package com.wonderfour.server.service.impl;

import com.wonderfour.server.entity.Post;
import com.wonderfour.server.service.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.rmi.runtime.Log;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class RecommendationServiceImplTest {

    @Autowired
    RecommendationService recommendationService;

    @Test
    void recommendByLikes() {
        List<Post> list = recommendationService.recommendByLikes();
        log.info(String.valueOf(list));
    }
}
