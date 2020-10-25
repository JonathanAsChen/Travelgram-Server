package com.wonderfour.server.exception;

import com.wonderfour.server.enums.ResultEnum;
import lombok.Getter;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/24/2020 4:35 PM
 */
@Getter
public class TravelgramException extends RuntimeException{

    private Integer code;

    public TravelgramException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();

    }

    public TravelgramException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
