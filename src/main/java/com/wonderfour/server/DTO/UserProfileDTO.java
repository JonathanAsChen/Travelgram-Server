package com.wonderfour.server.DTO;

import com.wonderfour.server.entity.Avatar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/10/2020 2:34 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {


    private String username;

    private String avatar;

}
