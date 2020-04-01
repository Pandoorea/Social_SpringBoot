package com.xwq.recruit.controller;

import entity.ResultCode;
import entity.ResultJson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 统一异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {
	
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultJson error(Exception e){
        e.printStackTrace();        
        return ResultJson.failed(ResultCode.FAILED,e.getCause().getMessage());
    }
}
