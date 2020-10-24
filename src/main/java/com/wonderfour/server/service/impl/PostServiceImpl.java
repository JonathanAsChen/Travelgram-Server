//package com.wonderfour.server.service.impl;
//
//import com.wonderfour.server.entity.Post;
//import com.wonderfour.server.entity.User;
//import com.wonderfour.server.repository.PostRepository;
//import com.wonderfour.server.repository.UserRepository;
//import com.wonderfour.server.service.PostService;
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author Yifan Chen
// * @version 1.0.0
// * @since 10/11/2020 10:04 AM
// */
//@Service
//public class PostServiceImpl implements PostService {
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public Post save(Post post) {
//        return postRepository.save(post);
//    }
//
//    @Override
//    public void deleteById(String id) {
//        postRepository.deleteById(id);
//    }
//
//    @Override
//    public boolean isSavedByUser(String username, String postId) {
//
//        return userRepository.findByUsernameAndFavoritesWithPostId(username, postId) != null;
//
//    }
//
//    @Override
//    public List<Post> findByAuthor(String author) {
//        return postRepository.findByAuthor(author);
//    }
//
//    @Override
//    public List<Post> findByTag(String tag) {
//        //TODO
//        return null;
//    }
//
//    @Override
//    public Post findById(String id) {
//        return postRepository.findById(id).orElse(null);
//    }
//}
