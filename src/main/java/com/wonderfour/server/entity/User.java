package com.wonderfour.server.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/7/2020 5:00 PM
 */
@Data
@Document(collection = "user")
@ToString
public class User implements UserDetails, Serializable {

    @Id
    private String id;

    private String username;

    private String password;

//    @DBRef
    private String avatar;

    @DBRef
    private List<Post> posts = new ArrayList<>();

    @DBRef
    private List<Post> favorites = new ArrayList<>();

    @DBRef
    private List<Post> liked = new ArrayList<>();;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        return Arrays.asList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
