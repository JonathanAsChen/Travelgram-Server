package com.wonderfour.server.service;

import com.wonderfour.server.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/23/2020 5:59 PM
 */
@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void isSavedByUser() {

        final String postId = "5f8e2e71edbe961d3a9082c9";
        final String fakePostId = "5f8e2e4dedbe961d3a9082c8";
        Post post = postService.findById(postId);
        assertNotNull(post);

        boolean isSaved = postService.isSavedByUser("test", postId);
        assertTrue(isSaved);

        assertFalse(postService.isSavedByUser("test", fakePostId));
    }
}
