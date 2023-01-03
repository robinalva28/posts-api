package com.example.postsapi.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Value("${springboot.application.name}")
    private String appName;

    @Bean
    public String getAppName(){
        return appName;
    }

}
