package com.wonderfour.server.service;

import com.wonderfour.server.entity.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/24/2020 5:17 PM
 */
@Service
public interface TagService {

    Tag findByTagName(String tagName);

    Tag findById(String id);

    String insert(String tagName);

    void savePostTagRelation(List<String> tagNames, String postId);

    List<String> findTagNameListByPostId(String postId);
}
