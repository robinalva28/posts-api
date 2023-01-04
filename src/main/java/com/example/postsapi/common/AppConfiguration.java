package com.example.postsapi.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

    @Value("${springboot.application.name}")
    private String appName;

    @Value("${jsonplaceholder.base-url}")
    private String jsonPlaceHolderUrl;

    @Bean
    public String getAppName() {
        return appName;
    }

    @Bean
    public String getJsonPlaceHolderUrl() {
        return jsonPlaceHolderUrl;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
