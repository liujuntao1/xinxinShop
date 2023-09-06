package com.xin.utils;

import com.xin.exception.GlobalException;

/**
 * @Author: ljt
 * @Date: 2023/9/6 14:47
 * @Description: Asserts
 * @Version 1.0.0 断言类
 */
public class Asserts {

    public static void fail(String msg) {
        throw new GlobalException(msg);
    }

    public static void isEmpty(Object[] array, String msg) {
        if (array == null || array.length == 0) {
            throw new GlobalException(msg);
        }
    }

    public static void isNull(Object o, String msg) {
        if (o == null) {
            throw new GlobalException(msg);
        }
    }

    public static void isTrue(boolean param, String msg) {
        if (param) {
            throw new GlobalException(msg);
        }
    }
}
