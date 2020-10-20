package com.wonderfour.server.service;

import com.wonderfour.server.DTO.PostDTO;
import com.wonderfour.server.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/11/2020 9:54 AM
 */
@Service
public interface PostService {

    Post save(Post post);

    List<Post> findByAuthor(String author);

    List<Post> findByTag(String tag);

    Post findById(String id);

    void deleteById(String id);
}
