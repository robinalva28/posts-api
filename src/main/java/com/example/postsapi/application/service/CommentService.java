package com.example.postsapi.application.service;

import com.example.postsapi.application.port.in.GetCommentByPostIdUseCase;
import com.example.postsapi.application.port.out.CommentPort;
import com.example.postsapi.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;

@Service
public class CommentService implements GetCommentByPostIdUseCase {

    private final CommentPort commentPort;

    @Autowired
    public CommentService(CommentPort commentPort) {
        this.commentPort = commentPort;
    }


    @Override
    public List<Comment> getCommentByPostId(GetCommentByPostIdCommand command) {

        var result = commentPort.getCommentsByPostId(command.getPostId());

        if (result.isEmpty()) {
            throw new NotFoundException("Comments not found");
        }

        return result;
    }
}
