package com.wonderfour.server.service.impl;

import com.wonderfour.server.dao.UserInfoMapper;
import com.wonderfour.server.entity.UserInfo;
import com.wonderfour.server.entity.UserInfoExample;
import com.wonderfour.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public UserInfo findById(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserInfo findByUsername(String username) {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UserInfo> userInfoList = userInfoMapper.selectByExample(example);
        return userInfoList.isEmpty() ? null : userInfoList.get(0);
    }

    @Override
    public int save(UserInfo user) {
        return userInfoMapper.insert(user);
    }

    @Override
    public void insert(UserInfo User) {
        userInfoMapper.insert(User);
    }

    @Override
    public void update(UserInfo User) {
        userInfoMapper.updateByPrimaryKey(User);
    }
}
