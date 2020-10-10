package com.wonderfour.server.VO;

import lombok.Data;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/10/2020 2:01 PM
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String message;

    private T data;
}
