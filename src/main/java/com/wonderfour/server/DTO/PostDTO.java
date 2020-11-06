package com.wonderfour.server.DTO;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class PostDTO {

    private String id;

    private UserProfileDTO user;

    private boolean saved;

    private boolean liked;

    @NotNull
    private String location;

    @NotNull
    private String title;

    @NotNull
    private String description;


    private List<String> tags = new ArrayList<>();


    private Long likes;

    private Long favorites;

    private Long comments;

    @NotNull
    private List<String> images = new ArrayList<>();

    private String article;
}
