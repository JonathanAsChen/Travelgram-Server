package com.wonderfour.server.service.impl;

import com.wonderfour.server.dao.PostMapper;
import com.wonderfour.server.dao.UserInfoMapper;
import com.wonderfour.server.entity.Post;
import com.wonderfour.server.entity.PostExample;
import com.wonderfour.server.entity.UserInfo;
import com.wonderfour.server.service.PostService;
import com.wonderfour.server.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/11/2020 10:04 AM
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserService userService;

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public boolean isSavedByUser(String username, String postId) {

        return true;

    }

    @Override
    public List<Post> findByAuthor(String author) {
        UserInfo userInfo = userService.findByUsername(author);
        PostExample example = new PostExample();
        example.createCriteria().andUserIdEqualTo(userInfo.getId());
        List<Post> posts = postMapper.selectByExample(example);
        return posts;
    }

    @Override
    public List<Post> findByTag(String tag) {
        //TODO
        return null;
    }

    @Override
    public Post findById(String id) {
        return null;
    }
}
