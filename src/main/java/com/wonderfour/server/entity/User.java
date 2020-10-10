package com.wonderfour.server.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/7/2020 5:00 PM
 */
@Data
@Document(collection = "user")
public class User {

    @Id
    private String id;

    private String username;

    private String password;

    @DBRef
    private List<String> posts;

}
