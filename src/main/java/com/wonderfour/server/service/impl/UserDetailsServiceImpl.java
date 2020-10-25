package com.wonderfour.server.service.impl;

import com.wonderfour.server.entity.UserInfo;
import com.wonderfour.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/8/2020 6:42 PM
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userService.findByUsername(username);
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(authority));
    }
}
