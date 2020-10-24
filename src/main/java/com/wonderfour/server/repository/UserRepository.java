package com.wonderfour.server.repository;

import com.wonderfour.server.entity.Post;
import com.wonderfour.server.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/7/2020 4:59 PM
 */
public interface UserRepository extends MongoRepository<User, String> {


    Optional<User> findByUsername(String username);

    @Query("{ 'posts.$id' : { $oid : ?1}}")
    User findByUsernameAndPostsWithPostId(String username, String postId);

    @Query("{ 'favorites.$id' : { $oid : ?1}}")
    User findByUsernameAndFavoritesWithPostId(String username, String postId);


}
