package com.wonderfour.server.service.impl;

import com.wonderfour.server.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/24/2020 1:27 PM
 */
@SpringBootTest
@Slf4j
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    void findByUsername() {
        UserInfo userInfo = userService.findByUsername("test1");
        log.info(String.valueOf(userInfo));

    }
}
