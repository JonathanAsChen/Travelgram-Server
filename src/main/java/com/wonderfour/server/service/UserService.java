package com.wonderfour.server.service;

import com.wonderfour.server.entity.UserInfo;
import org.springframework.stereotype.Service;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/7/2020 5:01 PM
 */
@Service
public interface UserService {

    UserInfo findByUsername(String username);

    UserInfo save(UserInfo user);

}
