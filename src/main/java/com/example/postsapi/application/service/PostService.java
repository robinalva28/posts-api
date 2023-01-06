package com.example.postsapi.application.service;

import com.example.postsapi.application.port.in.GetPostsUseCase;
import com.example.postsapi.application.port.out.PostPort;
import com.example.postsapi.domain.Post;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.List;

@Component
public class PostService implements GetPostsUseCase {
    final Logger log = Logger.getLogger(PostService.class);
    final PostPort postAdapter;

    @Autowired
    public PostService(PostPort postAdapter) {
        this.postAdapter = postAdapter;
    }

    @Override
    public List<Post> getPosts(GetPostsCommand command) {
        log.info("Service: call PostPort's method getAppPost()");
        var responseServ = postAdapter.getAllPosts(command.getOfset(), command.getLimit());
        if(responseServ.isEmpty()){
            throw new NotFoundException("Not found");
        }
        return responseServ;
    }
}
