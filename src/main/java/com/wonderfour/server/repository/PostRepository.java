package com.wonderfour.server.repository;

import com.wonderfour.server.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByAuthor(String author);


}
