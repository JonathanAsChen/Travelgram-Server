package com.wonderfour.server.controller;

import com.wonderfour.server.VO.ResultVO;
import com.wonderfour.server.entity.User;
import com.wonderfour.server.enums.ResultEnum;
import com.wonderfour.server.service.UserService;
import com.wonderfour.server.utils.ResultVOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/")
    public String home() {
        return "Welcome to travelgram server!";
    }

    @GetMapping("/login-success")
    public String loginSuccess() {
        return "Login success!";
    }

    @PostMapping("/register")
    public ResultVO register(User user) {
        if (service.findByUsername(user.getUsername()) != null) {
            return ResultVOUtils.error(ResultEnum.ERROR.getCode(), "Duplicate username.");
        }

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        service.create(user);
        return ResultVOUtils.success("User successfully registered.");
    }
}
