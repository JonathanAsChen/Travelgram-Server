package com.wonderfour.server.service.impl;

import com.wonderfour.server.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/31/2020 3:17 PM
 */
@SpringBootTest
@Slf4j
class CommentServiceImplTest {

    @Autowired
    private CommentServiceImpl commentService;

    @Test
    void getByPostId() {
        List<Comment> comment = commentService.getByPostId("1");
        log.info(String.valueOf(comment));

    }

    @Test
    void getById() {
        Comment comment = commentService.getById("111");
        log.info(String.valueOf(comment));
    }
}
