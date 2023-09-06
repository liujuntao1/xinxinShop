package com.xin.api;

import com.xin.enums.ResultCode;
import lombok.Data;

/**
 * @Author: ljt
 * @Date: 2023/9/6 11:15
 * @Description: CommonResult
 * @Version 1.0.0
 */
@Data
public class CommonResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public CommonResult() {
    }

    public CommonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonResult<T> success(String msg, T data) {
        return new CommonResult(ResultCode.success.getCode(), msg, data);
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult(ResultCode.success.getCode(), ResultCode.success.getMsg(), data);
    }

    public static <T> CommonResult<T> error(String msg) {
        return new CommonResult(ResultCode.error.getCode(), msg, null);
    }

    public static <T> CommonResult<T> error(String msg, T data) {
        return new CommonResult(ResultCode.error.getCode(), msg, data);
    }
}
