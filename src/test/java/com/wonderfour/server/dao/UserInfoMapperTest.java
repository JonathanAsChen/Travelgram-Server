package com.wonderfour.server.dao;

import com.wonderfour.server.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/24/2020 12:53 PM
 */
@SpringBootTest
class UserInfoMapperTest {

    @Autowired
    private UserInfoMapper mapper;


    @Test
    void insert() {
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword("password");
        userInfo.setUsername("test3");
        userInfo.setAvatar("123");
        mapper.insert(userInfo);
    }

    @Test
    void selectByPrimaryKey() {

        UserInfo userInfo = mapper.selectByPrimaryKey(1);
        assertNotNull(userInfo);
    }
}
