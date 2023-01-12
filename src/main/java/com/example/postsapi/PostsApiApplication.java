package com.example.postsapi;

import com.example.postsapi.adapter.out.persistence.comments.CommentEntity;
import com.example.postsapi.adapter.out.persistence.comments.CommentRepository;
import com.example.postsapi.adapter.out.persistence.posts.PostEntity;
import com.example.postsapi.adapter.out.persistence.posts.PostRepository;
import com.example.postsapi.application.port.out.CommentPort;
import com.example.postsapi.application.port.out.PostPort;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableMongoRepositories
public class PostsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostsApiApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(PostRepository postRepository, CommentRepository commentRepository,
                             PostPort postAdapter, CommentPort commentAdapter) {

        return args -> {
            var postResult = postAdapter.getAllPostsFromApi();
            var commentResult = commentAdapter.getAllCommentsFromApi();

            var entityList = postResult.parallelStream()
                    .map(r -> new PostEntity(r.getId(), r.getTitle(), r.getBody(), r.getUserId()))
                    .toList();
            var commentList = commentResult.parallelStream()
                    .map(r -> new CommentEntity(r.getName(), r.getEmail(), r.getBody(),r.getPostId(), LocalDateTime.now()))
                    .toList();

            postRepository.deleteAll();
            commentRepository.deleteAll();
            postRepository.insert(entityList);
            commentRepository.insert(commentList);
        };
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
