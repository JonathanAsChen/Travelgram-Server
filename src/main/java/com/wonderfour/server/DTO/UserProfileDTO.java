package com.wonderfour.server.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yifan Chen, Hongyu Su
 * @version 1.0.0
 * @since 10/10/2020 2:34 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {


    private String username;

    private String avatar;

    private String email;

    private Integer coin;

    private Integer gender;
}
