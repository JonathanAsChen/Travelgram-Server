package com.wonderfour.server.utils;

import com.wonderfour.server.VO.ResultVO;
import com.wonderfour.server.enums.ResultEnum;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/10/2020 2:04 PM
 */
public class ResultVOUtils {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setMessage("success");
        resultVO.setCode(ResultEnum.SUCCESS.getCode());

        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO success(String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMessage(msg);
        return resultVO;
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(msg);
        return resultVO;
    }

    public static ResultVO result(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMessage(resultEnum.getMsg());
        return resultVO;
    }
}
