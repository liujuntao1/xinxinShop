package com.xin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Author: ljt
 * @Date: 2023/9/6 11:05
 * @Description: XinXinAdminApplication
 * @Version 1.0.0
 */
@SpringBootApplication
@EnableWebMvc
public class XinXinAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(XinXinAdminApplication.class, args);
    }
}
