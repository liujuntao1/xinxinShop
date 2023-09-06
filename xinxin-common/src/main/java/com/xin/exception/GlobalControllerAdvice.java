package com.xin.exception;

import cn.hutool.log.Log;
import com.xin.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: ljt
 * @Date: 2023/9/6 13:51
 * @Description: GlobalControllerAdvice
 * @Version 1.0.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

//    @ExceptionHandler(Exception.class)
//    public CommonResult handler(Exception e) {
//        log.error(e.getMessage());
//        return CommonResult.error(e.getMessage());
//    }

    @ExceptionHandler(BusinessException.class)
    public CommonResult handler(BusinessException e) {
        return CommonResult.error("业务异常，" + e.getMessage());
    }

    @ExceptionHandler(GlobalException.class)
    public CommonResult handler(GlobalException e) {
        return CommonResult.error("系统异常，" + e.getMessage());
    }
}
