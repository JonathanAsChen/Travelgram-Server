package com.wonderfour.server.DTO;

import com.wonderfour.server.entity.Avatar;
import lombok.Data;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/10/2020 2:34 PM
 */
@Data
public class UserProfileDTO {


    private String username;

    private String avatar;

    public UserProfileDTO(String username, String avatar) {
        this.username = username;
        this.avatar = avatar;
    }
}
