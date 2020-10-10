package com.wonderfour.server.controller;

import com.wonderfour.server.entity.User;
import com.wonderfour.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/7/2020 5:21 PM
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService service;

    @GetMapping("/user/{username}")
    public User getUser(@PathVariable("username") String username) {
        return service.findByUsername(username);
    }

    @PostMapping("/register")
    public User register(User user) {
        return service.create(user);
    }
}
