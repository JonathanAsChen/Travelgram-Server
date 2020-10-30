package com.wonderfour.server.handler;

import com.wonderfour.server.VO.ResultVO;
import com.wonderfour.server.exception.TravelgramException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/24/2020 4:56 PM
 */
@ControllerAdvice
public class ServerExceptionHandler {


    @ExceptionHandler(TravelgramException.class)
    @ResponseBody
    public ResultVO doError(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                            Exception ex) {

        ResultVO result = new ResultVO<>();


        result.setCode(((TravelgramException) ex).getCode());
        result.setMessage(ex.getMessage());

        return result;
    }
}
