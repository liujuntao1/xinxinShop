package com.xin;

import cn.dev33.satoken.SaManager;
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
        System.out.println("启动成功，Sa-Token 配置如下：" + SaManager.getConfig());
    }
}
