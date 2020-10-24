package com.wonderfour.server.DTO;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostDTO {

    private String id;

    private UserProfileDTO user;

    //private Avatar avatar;

    private boolean saved;

    private String location;

    private String title;

    private String description;

    //@DBRef
    private List<String> tags = new ArrayList<>();

    private Long likes;

    private Long favorites;

    private Long comments;

    private String preview;

    private List<String> images = new ArrayList<>();

    private String article;
}
