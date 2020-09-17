package com.qy.wenlv.exception;

import com.qy.wenlv.constant.DefaultResultEnum;
import com.qy.wenlv.dto.DefaultResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author YunFengLiu
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public DefaultResult<Object> handleException(Exception e) {
        /*当时  1/0 时 异常 e  的各种信息*/
        System.out.println("getMessage  " + e.getMessage());
        System.out.println("e.toString  " + e.toString());
        return DefaultResult.fail(DefaultResultEnum.ERROR_500);
    }

    @ExceptionHandler(BusinessException.class)
    public DefaultResult<Object> businessException(BusinessException e) {
        return DefaultResult.fail(e.getCode(), e.getMsg());
    }

}