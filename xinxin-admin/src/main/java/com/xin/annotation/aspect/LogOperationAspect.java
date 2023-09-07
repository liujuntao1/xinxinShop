package com.xin.annotation.aspect;

import cn.dev33.satoken.stp.StpUtil;
import com.xin.annotation.LogOperation;
import com.xin.entity.sys.SysLog;
import com.xin.entity.sys.SysUser;
import com.xin.enums.LogTypeEnums;
import com.xin.mapper.sys.SysLogMapper;
import com.xin.mapper.sys.SysUserMapper;
import com.xin.utils.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private SysUserMapper sysUserMapper;

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
        int loginId = StpUtil.getLoginIdAsInt();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(loginId);
        if (sysUser != null) {
            //获取登录用户id、用户名、
            sysLog.setUserId(sysUser.getId());
            sysLog.setUserName(sysUser.getUserName());
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取操作ip
        sysLog.setOperationIp(IPUtils.getIpAddr(request));
        sysLogMapper.insertSelective(sysLog);
    }


}
