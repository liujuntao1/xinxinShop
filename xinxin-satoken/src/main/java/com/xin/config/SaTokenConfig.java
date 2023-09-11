package com.xin.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: ljt
 * @Date: 2023/9/7 13:44
 * @Description: SaTokenConfig
 * @Version 1.0.0
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    /**
     * 注册 Sa-Token 路由拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册 sa-Token拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new SaInterceptor(handle -> {
                    StpUtil.checkLogin();
                })).addPathPatterns("/**")
                .excludePathPatterns("/sysLogin/login",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/v3/**",
                        "/swagger-resources/**"
                        );

    }
}
