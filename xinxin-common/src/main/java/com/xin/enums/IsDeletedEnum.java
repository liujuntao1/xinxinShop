package com.xin.enums;

/**
 * @Author: ljt
 * @Date: 2023/9/6 17:33
 * @Description: IsDeletedEnum
 * @Version 1.0.0
 */
public enum IsDeletedEnum {
    Deleted(0, "已删除"),
    not_Deleted(1, "未删除");
    private Integer value;
    private String msg;

    IsDeletedEnum(Integer value, String msg) {
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
