package com.xin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: ljt
 * @Date: 2023/9/6 11:05
 * @Description: XinXinAdminApplication
 * @Version 1.0.0
 */
@SpringBootApplication
//开启事务
@EnableTransactionManagement
public class XinXinAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(XinXinAdminApplication.class, args);
    }
}
