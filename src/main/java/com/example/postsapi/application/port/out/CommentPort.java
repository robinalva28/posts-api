package com.example.postsapi.application.port.out;

import com.example.postsapi.adapter.out.client.CommentEntitiesResponse;
import com.example.postsapi.domain.Comment;

import java.util.List;

public interface CommentPort {

    List<CommentEntitiesResponse> getAllCommentsFromApi();
    List<Comment> getCommentsByPostId(Long postId);


}
