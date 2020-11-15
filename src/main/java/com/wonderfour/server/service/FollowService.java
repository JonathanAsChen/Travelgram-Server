package com.wonderfour.server.service;

import com.wonderfour.server.DTO.UserProfileDTO;
import com.wonderfour.server.entity.Follow;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 11/15/2020 4:52 PM
 */
public interface FollowService {
    List<UserProfileDTO> findFollowingByUserId(Integer userId);

    List<UserProfileDTO> findFollowerByUserId(Integer userId);

    Long countFollowerById(Integer userId) ;

    Long countFollowingById(Integer userId);

    void follow(Integer userId, Integer FollowingId);

    void unFollow(Integer userId, Integer FollowingId);

}
