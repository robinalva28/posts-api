package com.example.postsapi;

import com.example.postsapi.adapter.out.persistence.comments.CommentAdapter;
import com.example.postsapi.adapter.out.persistence.comments.CommentEntity;
import com.example.postsapi.adapter.out.persistence.comments.CommentRepository;
import com.example.postsapi.adapter.out.persistence.posts.PostAdapter;
import com.example.postsapi.adapter.out.persistence.posts.PostEntity;
import com.example.postsapi.adapter.out.persistence.posts.PostRepository;
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
                             PostAdapter postAdapter, CommentAdapter commentAdapter) {

        return args -> {
            var postResult = postAdapter.getAllPostsFromApi();
            var commentResult = commentAdapter.getAllCommentsFromApi();

            var entityList = postResult.stream()
                    .map(r -> new PostEntity(r.getTitle(), r.getBody(), r.getUserId(), LocalDateTime.now()))
                    .toList();
            var commentList = commentResult.stream()
                    .map(r -> new CommentEntity(r.getName(), r.getEmail(), r.getBody(),r.getPostId(), LocalDateTime.now()))
                    .toList();

            postRepository.deleteAll();
            commentRepository.deleteAll();
            postRepository.insert(entityList);
            commentRepository.insert(commentList);
        };
    }

}
