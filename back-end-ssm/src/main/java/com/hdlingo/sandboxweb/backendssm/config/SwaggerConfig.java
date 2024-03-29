package com.hdlingo.sandboxweb.backendssm.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description : Swagger 配置类
 *
 * @author : Pikachudy
 * @date : 2023/1/29 19:16
 */
@Configuration
public class SwaggerConfig {
    private static final String basePackage = "com.hdlingo.sandboxweb.backendssm.controller";

    @Bean
    public GroupedOpenApi usersGroup() {
        return GroupedOpenApi.builder()
                .group("users")
                .packagesToScan(basePackage)
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Components components = new Components();

        return new OpenAPI()
                .components(components)
                .info(apiInfo());
    }


    private Info apiInfo(){
        return new Info().title("SpringShop API")
                .description("Spring shop sample application")
                .version("v0.0.1")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));
    }
}