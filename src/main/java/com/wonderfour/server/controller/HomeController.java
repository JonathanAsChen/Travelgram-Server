package com.wonderfour.server.controller;

import com.wonderfour.server.DTO.PostDTO;
import com.wonderfour.server.DTO.UserProfileDTO;
import com.wonderfour.server.VO.ResultVO;
import com.wonderfour.server.entity.Post;
import com.wonderfour.server.entity.UserInfo;
import com.wonderfour.server.enums.ResultEnum;
import com.wonderfour.server.service.PostService;
import com.wonderfour.server.service.RecommendationService;
import com.wonderfour.server.service.UserService;
import com.wonderfour.server.utils.ResultVOUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yifan Chen, Hongyu Su
 * @version 1.0.0
 * @since 10/10/2020 9:43 AM
 */
@RestController
@RequestMapping("/")
public class HomeController {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService service;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String home() {
        return "Welcome to travelgram server!";
    }

    @GetMapping("/login-success")
    public ResultVO loginSuccess() {
        ResultVO resultVO = ResultVOUtils.success("Log in success.");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo userInfo = userService.findByUsername(authentication.getName());
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        BeanUtils.copyProperties(userInfo, userProfileDTO);
        resultVO.setData(userProfileDTO);
        return resultVO;
    }

    @Operation(description = "Register a user.")
    @PostMapping("/register")
    public ResultVO register(UserInfo userInfo) {
        if (service.findByUsername(userInfo.getUsername()) != null) {
            return ResultVOUtils.error(ResultEnum.ERROR.getCode(), "Duplicate username.");
        }

        userInfo.setPassword(BCrypt.hashpw(userInfo.getPassword(), BCrypt.gensalt()));
        service.insert(userInfo);
        return ResultVOUtils.success("User successfully registered.");
    }

    @Operation(description = "Get recommended post list.")
    @GetMapping("/api/recommendation")
    public ResultVO recommendation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo currUser = userService.findByUsername(authentication.getName());

        ResultVO<List<PostDTO>> resultVO = ResultVOUtils.success();
        List<PostDTO> postDTOList = new ArrayList<>();
        resultVO.setData(postDTOList);
        List<Post> postList = recommendationService.recommendByLikes();
        for (Post post : postList) {
            if (post == null) {
                continue;
            }
            PostDTO postDTO = postService.convert2DTO(currUser, post);
            postDTOList.add(postDTO);
        }
        return resultVO;
    }
}
