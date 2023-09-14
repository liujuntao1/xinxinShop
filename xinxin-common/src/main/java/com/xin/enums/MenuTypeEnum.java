package com.xin.enums;

/**
 * @Author: ljt
 * @Date: 2023/9/14 13:45
 * @Description: MenuTypeEnum
 * @Version 1.0.0
 */
public enum MenuTypeEnum {
    module(1, "模块"),

    menu(2, "菜单"),

    button(3, "按钮");

    private Integer value;
    private String msg;

    MenuTypeEnum(Integer value, String msg) {
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
