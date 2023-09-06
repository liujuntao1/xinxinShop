package com.xin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ljt
 * @Date: 2023/9/6 15:59
 * @Description: MybatisConfig
 * @Version 1.0.0
 */
@Configuration
@MapperScan(basePackages={"com.xin.mapper","com.xin.dao"})
public class MybatisConfig {
}
