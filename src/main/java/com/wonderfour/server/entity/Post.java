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

    @DBRef
    private User user;

    @DBRef
    private Avatar avatar;

    private boolean saved;

    private String location;

    private String title;

    private String description;

    @DBRef
    private List<Tag> tagList;

    private Long likes;

    private Long favorites;

    private Long comments;

    private String preview;

    private List<String> images;

    private Article article;
}
