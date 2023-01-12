package com.example.postsapi.application.service;

import com.example.postsapi.application.port.in.GetPostByIdUseCase;
import com.example.postsapi.application.port.in.GetPostsByTitleUseCase;
import com.example.postsapi.application.port.in.GetPostsUseCase;
import com.example.postsapi.application.port.out.PostPort;
import com.example.postsapi.domain.Post;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.List;

@Component
public class PostService implements GetPostsUseCase, GetPostByIdUseCase, GetPostsByTitleUseCase {
    final Logger log = Logger.getLogger(PostService.class);
    final PostPort postAdapter;

    @Autowired
    public PostService(PostPort postAdapter) {
        this.postAdapter = postAdapter;
    }

    @Override
    public List<Post> getPosts(GetPostsCommand command) {
        log.info("Service: call PostPort's method getAppPost()");

        var responseServ = postAdapter.getAllPosts(command.getOffset(), command.getLimit());
        if (responseServ.isEmpty()) {
            throw new NotFoundException("Posts not found");
        }
        return responseServ;
    }

    @Override
    public Post getPostById(GetPostByIdCommand command) {
        log.info("Service: call PostPort's method getPostById()");

        var response = postAdapter.getPostById(command.getId());
        if (response == null) {
            throw new NotFoundException("Post not found by id ".concat(command.getId().toString()));
        }
        return response;
    }

    @Override
    public List<Post> getPostsByTitle(GetPostsByTitleCommand command) {
        log.info("Service: call PostPort's method getPostByTitle()");

        var response = postAdapter.getPostsByTitle(command.getTitle());
        if (response.isEmpty()) {
            throw new NotFoundException("Not found posts by title ".concat(command.getTitle()));
        }
        return response;
    }


}
