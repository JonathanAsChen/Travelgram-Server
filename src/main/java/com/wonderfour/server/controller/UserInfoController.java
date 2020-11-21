package com.wonderfour.server.controller;

import com.wonderfour.server.DTO.FollowCountDTO;
import com.wonderfour.server.DTO.RewardDTO;
import com.wonderfour.server.DTO.UserProfileDTO;
import com.wonderfour.server.VO.ResultVO;
import com.wonderfour.server.entity.UserInfo;
import com.wonderfour.server.service.FollowService;
import com.wonderfour.server.service.UserService;
import com.wonderfour.server.utils.ResultVOUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/7/2020 5:21 PM
 */
@RestController
@RequestMapping("/api")
public class UserInfoController {

    @Qualifier("userServiceImpl")

    @Autowired
    private UserService userService;

    @Autowired
    private FollowService followService;

    @Operation(description = "Get current logged in user info.")
    @GetMapping("/currUser")
    public UserProfileDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo currUser = userService.findByUsername(authentication.getName());
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        BeanUtils.copyProperties(currUser, userProfileDTO);
        return userProfileDTO;
    }

    @Operation(description = "Get user info by input username.")
    @GetMapping("/user/{username}")
    public UserInfo getUser(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }

    @Operation(description = "Create new user.")
    @PostMapping("/user/new")
    public ResultVO insertUser(UserInfo userInfo) {
        try {
            userService.insert(userInfo);
            return ResultVOUtils.success("Insert success.");
        } catch (Exception exception) {
            return ResultVOUtils.error(500, "Wrong");
        }
    }

    @Operation(description = "Update user info.")
    @PutMapping("/user/update")
    public ResultVO updateUser(UserInfo userInfo) {
        try {
            userService.update(userInfo);
            return ResultVOUtils.success("update success.");
        } catch (Exception exception) {
            return ResultVOUtils.error(500, "Wrong");
        }
    }


    @Operation(description = "Get follow count info")
    @GetMapping("/user/{username}/getFollowCount")
    public ResultVO getFollowCount(@PathVariable("username") String username) {

        UserInfo userInfo = userService.findByUsername(username);
        Integer userId = userInfo.getId();
        FollowCountDTO followCountDTO = new FollowCountDTO();
        followCountDTO.setFollowerCount(followService.countFollowerById(userId));
        followCountDTO.setFollowingCount(followService.countFollowingById(userId));
        ResultVO<FollowCountDTO> resultVO = ResultVOUtils.success();
        resultVO.setData(followCountDTO);

        return resultVO;
    }

    @Operation(description = "Find user")
    @GetMapping("/user/{username}/getFollowingUsers")
    public ResultVO getFollowingUsers(@PathVariable("username") String username) {
        UserInfo userInfo = userService.findByUsername(username);
        Integer userId = userInfo.getId();
        ResultVO<List<UserProfileDTO>> resultVO = ResultVOUtils.success();
        resultVO.setData(followService.findFollowingByUserId(userId));
        return resultVO;
    }

    @Operation(description = "Find follower")
    @GetMapping("/user/{username}/getFollower")
    public ResultVO getFollower(@PathVariable("username") String username) {
        UserInfo userInfo = userService.findByUsername(username);
        Integer userId = userInfo.getId();
        ResultVO<List<UserProfileDTO>> resultVO = ResultVOUtils.success();
        resultVO.setData(followService.findFollowerByUserId(userId));
        return resultVO;
    }

    @PostMapping("/user/follow/{username}")
    @PreAuthorize("authentication.authenticated")
    public ResultVO follow(@PathVariable("username") String username) {
        UserInfo following = userService.findByUsername(username);

        UserInfo currUser = userService.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());

        followService.follow(currUser.getId(), following.getId());
        return ResultVOUtils.success();
    }

    @PostMapping("/user/unfollow/{username}")
    @PreAuthorize("authentication.authenticated")
    public ResultVO unfollow(@PathVariable("username") String username) {
        UserInfo following = userService.findByUsername(username);

        UserInfo currUser = userService.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());

        followService.unFollow(currUser.getId(), following.getId());
        return ResultVOUtils.success();
    }

    @PostMapping("/user/reward")
    @PreAuthorize("authentication.authenticated")
    public ResultVO reward(RewardDTO rewardDTO) {
        UserInfo currUser = userService.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());

        UserInfo uploader = userService.findByUsername(rewardDTO.getUploader());
        userService.reward(currUser, uploader, rewardDTO.getValue());

        return ResultVOUtils.success("Successfully reward " + rewardDTO.getUploader());
    }
}
