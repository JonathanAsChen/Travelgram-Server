package com.wonderfour.server.DTO;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
public class MockPostDTO {

    private String id;

    private String username;

    private String avatar;

    private boolean saved;

    private String location;

    private String title;

    private String description;

    @DBRef
    private String tags;

    private Long likes;

    private Long favorites;

    private Long comments;

    private String preview;

    private String images;

    private String article;
}
