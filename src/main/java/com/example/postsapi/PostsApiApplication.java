package com.example.postsapi;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.Collections;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.postsapi.adapter.out")
public class PostsApiApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PostsApiApplication.class);
        String port = System.getenv("PORT");
        app.setDefaultProperties(Collections.singletonMap("server.port", port == null ? "8080" : port));
        app.run(args);
    }

    @Bean
    public Validator validator() {
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory()
                .getValidator();
    }
}
