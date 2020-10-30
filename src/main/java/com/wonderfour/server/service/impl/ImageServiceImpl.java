package com.wonderfour.server.service.impl;

import com.wonderfour.server.dao.ImageMapper;
import com.wonderfour.server.entity.Image;
import com.wonderfour.server.entity.ImageExample;
import com.wonderfour.server.service.ImageService;
import com.wonderfour.server.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/25/2020 9:03 AM
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public List<String> findUrlByPostId(String postId) {

        ImageExample example = new ImageExample();
        example.createCriteria().andPostIdEqualTo(postId);
        List<Image> images = imageMapper.selectByExample(example);
        return images.stream().map(Image::getUrl).collect(Collectors.toList());
    }

    @Override
    public void insert(List<String> imageUrls, String postId) {

        for (String url : imageUrls) {
            Image image = new Image();
            image.setId(KeyUtil.genUniqueKey());
            image.setPostId(postId);
            image.setUrl(url);
            imageMapper.insert(image);
        }

    }
}
