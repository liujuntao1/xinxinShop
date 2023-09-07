package com.xin.exception;

import cn.dev33.satoken.exception.SaTokenException;
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

    // 全局异常拦截
    @ExceptionHandler
    public CommonResult handlerException(Exception e) {
        e.printStackTrace();
        return CommonResult.error(e.getMessage());
    }

    @ExceptionHandler(SaTokenException.class)
    public CommonResult handler(SaTokenException e) {
        e.printStackTrace();
        return CommonResult.error("用户权限认证失败！" + e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public CommonResult handler(BusinessException e) {
        e.printStackTrace();
        return CommonResult.error("业务异常！" + e.getMessage());
    }

    @ExceptionHandler(GlobalException.class)
    public CommonResult handler(GlobalException e) {
        e.printStackTrace();
        return CommonResult.error("系统异常！" + e.getMessage());
    }
}
