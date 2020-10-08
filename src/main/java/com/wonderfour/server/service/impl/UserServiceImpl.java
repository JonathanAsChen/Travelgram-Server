package com.wonderfour.server.service.impl;

import com.wonderfour.server.entity.User;
import com.wonderfour.server.repository.UserRepository;
import com.wonderfour.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/7/2020 5:09 PM
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    @Override
    public User create(User user) {
        User saved = repository.save(user);
        return saved;
    }
}
