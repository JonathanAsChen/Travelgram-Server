package com.wonderfour.server.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "post")
public class Post {
    @Id
    private String id;

    private String author;

    private String avatar;

    private String location;

    private String title;

    private String description;

    private List<String> tags;

    private Long likes;

    private Long favorites;

    @DBRef
    private List<Comment> comments;

    private String preview;

    private List<String> images;

    private Article article;
}
