package com.wonderfour.server.utils;

import com.wonderfour.server.DTO.PostDTO;
import com.wonderfour.server.DTO.UserProfileDTO;
import com.wonderfour.server.entity.Post;
import org.springframework.beans.BeanUtils;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/19/2020 9:18 PM
 */
public class Post2PostDTOConverter {

    public static PostDTO convert(Post post) {
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setAvatar(post.getAvatar());
        userProfileDTO.setUsername(post.getAuthor());
        postDTO.setUser(userProfileDTO);
        return postDTO;
    }
}
