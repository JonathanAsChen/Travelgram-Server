package com.wonderfour.server.repository;

import com.wonderfour.server.entity.Post;
import com.wonderfour.server.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/20/2020 11:03 AM
 */
@SpringBootTest
@Slf4j
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PostRepository postRepository;

    @Test
    void findByUsernameAndFavoritesContaining() {

        Post post = postRepository.findById("5f8e2e71edbe961d3a9082c9").orElse(null);
        User user = repository.findByUsernameAndPostsWithPostId("test", "5f8e2e71edbe961d3a9082c9");
        log.info(String.valueOf(user));
    }



}
