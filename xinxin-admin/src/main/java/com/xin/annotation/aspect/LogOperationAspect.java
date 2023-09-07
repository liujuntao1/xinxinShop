package com.xin.annotation.aspect;

import com.xin.annotation.LogOperation;
import com.xin.entity.sys.SysLog;
import com.xin.enums.LogTypeEnums;
import com.xin.mapper.sys.SysLogMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: ljt
 * @Date: 2023/9/7 09:37
 * @Description: LogOperationAspect
 * @Version 1.0.0 日志切面类
 */
@Aspect
@Component
public class LogOperationAspect {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Pointcut("@annotation(com.xin.annotation.LogOperation)")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void logBefore(JoinPoint joinPoint) {
        SysLog sysLog = new SysLog();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        LogOperation annotation = signature.getMethod().getAnnotation(LogOperation.class);
        sysLog.setLogContent(annotation.value());
        sysLog.setLogType(LogTypeEnums.operation_log.getValue());
        //TODO 获取登录用户id、用户名、以及操作ip
//        sysLog.setOperationIp();
//        sysLog.setUserId();
//        sysLog.setUserName();
        sysLogMapper.insertSelective(sysLog);

    }
}
