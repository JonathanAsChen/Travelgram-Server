package com.wonderfour.server.DTO;

import lombok.Data;

/**
 * @author Yifan Chen, Hongyu Su
 * @version 1.0.0
 * @since 11/15/2020 5:23 PM
 */
@Data
public class FollowCountDTO {

    private Long followingCount;

    private Long followerCount;
}
