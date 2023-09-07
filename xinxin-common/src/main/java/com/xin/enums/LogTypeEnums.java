package com.xin.enums;

/**
 * @Author: ljt
 * @Date: 2023/9/7 09:45
 * @Description: LogTypeEnums
 * @Version 1.0.0
 */
public enum LogTypeEnums {
    operation_log(1, "操作日志"),
    login_log(2, "登录日志");
    private Integer value;
    private String msg;

    LogTypeEnums(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public Integer getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }
}
