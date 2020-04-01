package com.xwq.base.controller;

import entity.IErrorCode;
import entity.ResultCode;
import entity.ResultJson;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: Xiang Wenqin
 * @DateTime: 2020/3/17 14:05
 */
@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResultJson exception(Exception e){
        return ResultJson.failed(ResultCode.FAILED,e.getMessage());
    }
}
