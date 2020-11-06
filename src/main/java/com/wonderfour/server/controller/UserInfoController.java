package com.wonderfour.server.controller;

import com.wonderfour.server.DTO.UserProfileDTO;
import com.wonderfour.server.VO.ResultVO;
import com.wonderfour.server.entity.UserInfo;
import com.wonderfour.server.service.UserService;
import com.wonderfour.server.utils.ResultVOUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    private UserService service;

    @Operation(description = "Get current logged in user info.")
    @GetMapping("/currUser")
    public UserProfileDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo currUser = service.findByUsername(authentication.getName());
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        BeanUtils.copyProperties(currUser, userProfileDTO);
        return userProfileDTO;
    }

    @Operation(description = "Get user info by input username.")
    @GetMapping("/user/{username}")
    public UserInfo getUser(@PathVariable("username") String username) {
        return service.findByUsername(username);
    }

    @Operation(description = "Create new user.")
    @PostMapping("/user/new")
    public ResultVO insertUser(UserInfo userInfo) {
        try {
            service.insert(userInfo);
            return ResultVOUtils.success("Insert success.");
        } catch (Exception exception) {
            return ResultVOUtils.error(500, "Wrong");
        }
    }

    @Operation(description = "Update user info.")
    @PutMapping("/user/update")
    public ResultVO updateUser(UserInfo userInfo) {
        try {
            // ????牛逼啊写起来啊，凑弟弟
            service.update(userInfo);
            return ResultVOUtils.success("update success.");
        } catch (Exception exception) {
            return ResultVOUtils.error(500, "Wrong");
        }
    }

}
