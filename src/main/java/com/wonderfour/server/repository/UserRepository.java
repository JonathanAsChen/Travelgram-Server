package com.wonderfour.server.repository;

import com.wonderfour.server.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/7/2020 4:59 PM
 */
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
