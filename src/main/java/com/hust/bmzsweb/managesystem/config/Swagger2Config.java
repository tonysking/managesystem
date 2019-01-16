package com.hust.bmzsweb.managesystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    /*
        配置swagger2基本内容
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .host("localhost:8080")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hust.bmzsweb.managesystem.controller"))//controller路径
                .paths(PathSelectors.any()).build();
    }
    /*
        构建api文档信息
     */
    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                //设置页面标题
                .title("报名助手小程序api")
                //设置联系人
                .contact(new Contact("software23","www.software23.com","software23@hust.com"))
                //描述
                .description("欢迎使用报名助手小程序api")
                //版本号
                .version("1.0")
                .build();

    }

}
