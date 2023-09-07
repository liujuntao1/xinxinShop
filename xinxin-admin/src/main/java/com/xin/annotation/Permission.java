package com.xin.annotation;

import java.lang.annotation.*;

/**
 * @Author: ljt
 * @Date: 2023/9/7 15:08
 * @Description: Persimission
 * @Version 1.0.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
    /**
     * 权限值
     *
     * @return
     */
    String value() default "";
}
