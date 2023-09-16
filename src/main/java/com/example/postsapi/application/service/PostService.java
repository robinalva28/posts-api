package com.example.postsapi.application.service;

import com.example.postsapi.application.port.in.GetCommentByPostIdUseCase;
import com.example.postsapi.application.port.in.GetPostByIdUseCase;
import com.example.postsapi.application.port.in.GetPostsByTitleUseCase;
import com.example.postsapi.application.port.in.GetPostsUseCase;
import com.example.postsapi.application.port.out.PostPort;
import com.example.postsapi.domain.Comment;
import com.example.postsapi.domain.Post;
import jakarta.validation.Valid;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.List;

@Component
public class PostService implements GetPostsUseCase, GetPostByIdUseCase, GetPostsByTitleUseCase, GetCommentByPostIdUseCase {
    final Logger log = Logger.getLogger(PostService.class);
    final PostPort postPort;

    @Autowired
    public PostService(PostPort postPort) {
        this.postPort = postPort;
    }

    @Override
    public Page<Post> getPosts(@Valid GetPostsCommand command) {
        var responseServ = postPort.getAllPosts(command.getPage(), command.getPageSize());
        if (responseServ.isEmpty()) {
            throw new NotFoundException("Posts not found");
        }
        return responseServ;
    }

    @Override
    public Post getPostById(@Valid GetPostByIdCommand command) {
        log.info("Service: call PostPort's method getPostById()");

        var response = postPort.getPostById(command.getId());
        if (response == null) {
            throw new NotFoundException("Post not found by id ".concat(command.getId().toString()));
        }
        return response;
    }

    @Override
    public List<Post> getPostsByTitle(@Valid GetPostsByTitleCommand command) {
        log.info("Service: call PostPort's method getPostByTitle()");

        var response = postPort.getPostsByTitle(command.getTitle());
        if (response.isEmpty()) {
            throw new NotFoundException("Not found posts by title ".concat(command.getTitle()));
        }
        return response;
    }

    @Override
    public List<Comment> getCommentByPostId(@Valid GetCommentByPostIdCommand command) {

        var result = postPort.getCommentsByPostId(command.getPostId());

        if (result.isEmpty()) {
            throw new NotFoundException("Comments not found");
        }

        return result;
    }
}
