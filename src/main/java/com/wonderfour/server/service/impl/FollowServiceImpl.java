package com.wonderfour.server.service.impl;

import com.wonderfour.server.DTO.UserProfileDTO;
import com.wonderfour.server.dao.FollowMapper;
import com.wonderfour.server.entity.Follow;
import com.wonderfour.server.entity.FollowExample;
import com.wonderfour.server.entity.UserInfo;
import com.wonderfour.server.service.FollowService;
import com.wonderfour.server.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 11/15/2020 4:54 PM
 */
@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<UserProfileDTO> findFollowingByUserId(Integer userId) {

        FollowExample followExample = new FollowExample();
        followExample.createCriteria().andFollowerIdEqualTo(userId);

        List<Follow> followList = followMapper.selectByExample(followExample);
        List<UserProfileDTO> profileDTOS = new ArrayList<>();

        for (Follow followInfo : followList) {
            UserInfo userInfo = userService.findById(followInfo.getUserId());
            UserProfileDTO userProfileDTO = new UserProfileDTO();
            BeanUtils.copyProperties(userInfo, userProfileDTO);
            profileDTOS.add(userProfileDTO);
        }

        return profileDTOS;
    }

    @Override
    public List<UserProfileDTO> findFollowerByUserId(Integer userId) {
        FollowExample followExample = new FollowExample();
        followExample.createCriteria().andUserIdEqualTo(userId);

        List<Follow> followList = followMapper.selectByExample(followExample);
        List<UserProfileDTO> profileDTOS = new ArrayList<>();

        for (Follow followInfo : followList) {
            UserInfo userInfo = userService.findById(followInfo.getFollowerId());
            UserProfileDTO userProfileDTO = new UserProfileDTO();
            BeanUtils.copyProperties(userInfo, userProfileDTO);
            profileDTOS.add(userProfileDTO);
        }

        return profileDTOS;

    }

    @Override
    public Long countFollowerById(Integer userId) {
        FollowExample followExample = new FollowExample();
        followExample.createCriteria().andUserIdEqualTo(userId);
        Long result = followMapper.countByExample(followExample);
        return result;
    }

    @Override
    public Long countFollowingById(Integer userId) {
        FollowExample followExample = new FollowExample();
        followExample.createCriteria().andFollowerIdEqualTo(userId);
        Long result = followMapper.countByExample(followExample);
        return result;
    }

    @Override
    public void follow(Integer userId, Integer FollowingId) {
        Follow follow = new Follow();
        follow.setFollowerId(userId);
        follow.setUserId(FollowingId);
        followMapper.insert(follow);
    }

    @Override
    public void unFollow(Integer userId, Integer FollowingId) {
        FollowExample followExample = new FollowExample();
        followExample.createCriteria().andFollowerIdEqualTo(userId)
                .andUserIdEqualTo(FollowingId);
        followMapper.deleteByExample(followExample);
    }
}
