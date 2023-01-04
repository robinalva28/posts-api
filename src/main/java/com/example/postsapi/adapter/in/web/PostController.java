package com.example.postsapi.adapter.in.web;

import com.example.postsapi.application.port.in.GetPostsUseCase;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/posts")
public class PostController {

    final Logger log = Logger.getLogger(PostController.class);
    final GetPostsUseCase getPostsUseCase;

    @Autowired
    public PostController(GetPostsUseCase getPostsUseCase) {
        this.getPostsUseCase = getPostsUseCase;
    }

    @GetMapping("/posts")
    public Object getPosts() {

        log.info("call getPostUseCase...");
        return getPostsUseCase.getAllPosts();
    }

}
