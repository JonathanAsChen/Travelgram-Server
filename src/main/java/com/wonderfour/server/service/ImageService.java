package com.wonderfour.server.service;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/25/2020 8:57 AM
 */

public interface ImageService {

    List<String> findUrlByPostId(String postId);

    void insert(List<String> imageUrls, String postId);
}
