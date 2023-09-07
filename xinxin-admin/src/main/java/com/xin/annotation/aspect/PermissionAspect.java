package com.xin.annotation.aspect;

import cn.dev33.satoken.stp.StpUtil;
import com.xin.annotation.Permission;
import com.xin.utils.Asserts;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @Author: ljt
 * @Date: 2023/9/7 15:09
 * @Description: PermissionAspect
 * @Version 1.0.0
 */
@Component
@Aspect
public class PermissionAspect {

    @Pointcut("@annotation(com.xin.annotation.Permission)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void handler(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Permission annotation = methodSignature.getMethod().getAnnotation(Permission.class);
        String value = annotation.value();
        if (!StpUtil.hasPermission(value)) {
            Asserts.fail("用户无此权限！");
        }
    }

}
