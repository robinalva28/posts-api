package com.example.postsapi.application.service;

import com.example.postsapi.application.port.in.GetPostsUseCase;
import com.example.postsapi.application.port.out.PostPort;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostService implements GetPostsUseCase {
    final Logger log = Logger.getLogger(PostService.class);
    final PostPort postAdapter;

    @Autowired
    public PostService(PostPort postAdapter) {
        this.postAdapter = postAdapter;
    }

    @Override
    public Object getAllPosts() {
        log.info("Service: call PostPort's method getAppPost()");
        return postAdapter.getAllPosts();
    }
}
