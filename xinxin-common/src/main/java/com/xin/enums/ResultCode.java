package com.xin.enums;

/**
 * @Author: ljt
 * @Date: 2023/9/6 13:43
 * @Description: ResultCode
 * @Version 1.0.0
 */
public enum ResultCode {
    success(0, "操作成功！"),
    error(500, "操作失败！"),
    validate_error(500, "参数校验异常！");

    private final Integer code;
    private final String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
