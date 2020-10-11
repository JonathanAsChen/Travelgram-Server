package com.wonderfour.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/10/2020 8:25 PM
 */
@Document(collection = "comment")
@Data
@AllArgsConstructor
public class Comment {

    @Id
    private String id;

    private String author;

    private String avatar;

    private String content;
}
