package com.wonderfour.server.service;

import com.wonderfour.server.DTO.PostDTO;
import com.wonderfour.server.entity.Post;
import com.wonderfour.server.entity.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/11/2020 9:54 AM
 */
@Service
public interface PostService {

    String save(Post post);

    int update(Post post);

    List<Post> findByAuthor(String author);

    List<Post> findByTag(String tag);

    Post findById(String id);

    void deleteById(String id);

    boolean isSavedByUser(String username, String postId);

    boolean isLikedByUser(String username, String postId);

    PostDTO convert2DTO(UserInfo user, UserInfo author, Post post);

    PostDTO convert2DTO(UserInfo author, Post post);

    Long countFavorites(String postId);

    Long countLikes(String postId);


}
