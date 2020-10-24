package com.wonderfour.server.service.impl;

import com.wonderfour.server.dao.UserInfoMapper;
import com.wonderfour.server.entity.UserInfo;
import com.wonderfour.server.entity.UserInfoExample;
import com.wonderfour.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/7/2020 5:09 PM
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo findByUsername(String username) {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andUsernameEqualTo(username);
        return userInfoMapper.selectByExample(example).get(0);
    }

    @Override
    public UserInfo save(UserInfo user) {
        return null;
    }
}
