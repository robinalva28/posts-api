package com.example.postsapi;

import com.example.postsapi.adapter.out.persistence.comments.CommentEntity;
import com.example.postsapi.adapter.out.persistence.comments.CommentRepository;
import com.example.postsapi.adapter.out.persistence.posts.PostEntity;
import com.example.postsapi.adapter.out.persistence.posts.PostRepository;
import com.example.postsapi.application.port.out.CommentPort;
import com.example.postsapi.application.port.out.PostPort;
import jakarta.transaction.Transactional;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.postsapi.adapter.out")
public class PostsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostsApiApplication.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner runner(PostRepository postRepository, CommentRepository commentRepository,
                             PostPort postAdapter, CommentPort commentAdapter) {

        return args -> {
            var postResult = postAdapter.getAllPostsFromApi();
            var commentResult = commentAdapter.getAllCommentsFromApi();

            var postEntityList = postResult.parallelStream()
                    .map(r -> new PostEntity(r.getTitle(), r.getBody(), r.getUserId()))
                    .toList();

            postRepository.deleteAll();
            postRepository.saveAllAndFlush(postEntityList);

            var postEntityPersisted = postRepository.findAll();

            var commentEntityList = commentResult.parallelStream()
                    .map(r -> new CommentEntity(r.getName(), r.getEmail(), r.getBody(), postEntityPersisted
                            .stream()
                            .filter(p -> (long) p.getId() == r.getPostId()).toList().get(0))
                    )
                    .toList();

            commentRepository.deleteAll();
            commentRepository.saveAllAndFlush(commentEntityList);
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
