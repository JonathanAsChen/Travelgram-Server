package com.wonderfour.server.service.impl;

import com.wonderfour.server.entity.Post;
import com.wonderfour.server.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/24/2020 1:36 PM
 */
@Slf4j
@SpringBootTest
class PostServiceImplTest {

    @Autowired
    private PostService postService;

    @Test
    void findByAuthor() {

        List<Post> posts = postService.findByAuthor("test1");
        log.info(String.valueOf(posts));
    }

    @Test
    void findByTag() {
        List<Post> posts = postService.findByTag("Peregit Ampyca");
        log.info(String.valueOf(posts));
    }

    @Test
    void isSaved() {

        boolean isSaved = postService.isSavedByUser("test2", "1");
        boolean isSaved2 = postService.isSavedByUser("test2", "2");
        return;
    }
}
