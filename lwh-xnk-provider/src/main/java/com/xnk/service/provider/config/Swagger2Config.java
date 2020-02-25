package com.xnk.service.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>
 * <b>功能：</b><br>
 * <b>作者：</b>@author 子悠<br>
 * <b>日期：</b>2019-03-28 23:35<br>
 * <b>详细说明：</b>Swagger配置类，
 * UI地址：       http://127.0.0.1:8655/swagger-ui.html
 * JSON文档地址   http://127.0.0.1:8655/v2/api-docs
 * <br>
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        //header中增加 token_id参数，没有可以去掉
        ParameterBuilder token = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        token.name("token_id").description("user token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        pars.add(token.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 指定controller存放的目录路径
                .apis(RequestHandlerSelectors.basePackage("com.xnk.service.provider.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("鞋凝客")
                // 文档描述
                .description("文档详细信息如下")
                .termsOfServiceUrl("这里可以增加需求文档 wiki 地址")
                .version("v0.0.1")//定义版本号
                .build();
    }

}
