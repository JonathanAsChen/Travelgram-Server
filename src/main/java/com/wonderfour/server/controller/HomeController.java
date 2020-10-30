package com.wonderfour.server.controller;

import com.wonderfour.server.DTO.PostDTO;
import com.wonderfour.server.VO.ResultVO;
import com.wonderfour.server.entity.Post;
import com.wonderfour.server.entity.UserInfo;
import com.wonderfour.server.enums.ResultEnum;
import com.wonderfour.server.service.PostService;
import com.wonderfour.server.service.RecommendationService;
import com.wonderfour.server.service.UserService;
import com.wonderfour.server.utils.ResultVOUtils;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yifan Chen
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
    public String loginSuccess() {
        return "Login success!";
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
        ResultVO<List<PostDTO>> resultVO = ResultVOUtils.success();
        List<PostDTO> postDTOList = new ArrayList<>();
        resultVO.setData(postDTOList);
        List<Post> postList = recommendationService.recommendByLikes();
        for (Post post : postList) {
            PostDTO postDTO = postService.convert2DTO(userService.findById(post.getUserId()), post);
            postDTOList.add(postDTO);
        }
        return resultVO;
    }
}
