package com.wonderfour.server.enums;

import lombok.Getter;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/10/2020 2:03 PM
 */
@Getter
public enum ResultEnum {

    SUCCESS(100, "success"),

    ERROR(200, "failed"),

    FORBIDDEN(403, "Access denied."),

    SERVER_ERROR(500, "Internal server error."),

    TAG_NOT_FOUND(900, "Tag not found."),

    POST_NOT_FOUND(901, "Post not found."),



    ;


    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
