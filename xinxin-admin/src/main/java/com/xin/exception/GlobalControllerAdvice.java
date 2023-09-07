package com.xin.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
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

    @ExceptionHandler(SaTokenException.class)
    public CommonResult handler(SaTokenException e) {
        return CommonResult.error("用户权限认证失败！" + e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public CommonResult handler(BusinessException e) {
        return CommonResult.error("业务异常！" + e.getMessage());
    }

    @ExceptionHandler(GlobalException.class)
    public CommonResult handler(GlobalException e) {
        return CommonResult.error("系统异常！" + e.getMessage());
    }
}
