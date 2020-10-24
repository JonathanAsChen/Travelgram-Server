package com.wonderfour.server.service.impl;

import com.wonderfour.server.entity.Post;
import com.wonderfour.server.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
}
