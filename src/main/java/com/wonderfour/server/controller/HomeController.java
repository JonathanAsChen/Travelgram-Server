package com.wonderfour.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/")
    public String home() {
        return "Welcome to travelgram server!";
    }

    @GetMapping("/login-success")
    public String loginSuccess() {
        return "Login success!";
    }
}
