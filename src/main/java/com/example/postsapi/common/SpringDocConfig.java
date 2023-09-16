package com.example.postsapi.common;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new io.swagger.v3.oas.models.info.Info()
                                .title("Posts-Comments API")
                                .version("1.0.0")
                                .description("Posts API implemented with Spring Boot RESTful service and documented using springdoc-openapi and OpenAPI 3."));
    }
}
