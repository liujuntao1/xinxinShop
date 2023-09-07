package com.xin.annotation;

import java.lang.annotation.*;

/**
 * @Author: ljt
 * @Date: 2023/9/7 09:32
 * @Description: LogOperation
 * @Version 1.0.0
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LogOperation {
    /**
     * 日志内容
     *
     * @return
     */
    String value() default "";
}
