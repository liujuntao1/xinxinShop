package com.xin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ljt
 * @Date: 2023/9/6 13:57
 * @Description: SwaggerConfig
 * @Version 1.0.0
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30).apiInfo(getApiInfo())
                .enable(true).select()
                .apis(RequestHandlerSelectors.basePackage("com.xin.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalResponses(HttpMethod.GET, getResponses());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("欣欣商城后台接口").description("商城后台接口使用文档").contact(new Contact("xinxin", "wwww.baidu.com", "xinxin@qq.com")).version("v1.0").build();
    }

    public List<Response> getResponses() {
        ArrayList<Response> responses = new ArrayList<>();
        responses.add(new ResponseBuilder().code("404").description("未找到！").build());
        return responses;
    }
}
