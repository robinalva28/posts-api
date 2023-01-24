package com.example.postsapi.adapter.out.persistence.comments;

import com.example.postsapi.adapter.out.client.CommentEntitiesResponse;
import com.example.postsapi.adapter.out.client.JsonPlaceHolderRestClient;
import com.example.postsapi.application.port.out.CommentPort;
import com.example.postsapi.domain.Comment;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentAdapter implements CommentPort {

    final Logger log = Logger.getLogger(com.example.postsapi.adapter.out.persistence.posts.PostAdapter.class);

    private final JsonPlaceHolderRestClient jsonPlaceHolderRestClient;

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentAdapter(JsonPlaceHolderRestClient jsonPlaceHolderRestClient, CommentMapper commentMapper, CommentRepository commentRepository) {
        this.jsonPlaceHolderRestClient = jsonPlaceHolderRestClient;
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentEntitiesResponse> getAllCommentsFromApi() {
        log.info("Adapter: requesting restClient...");
        return jsonPlaceHolderRestClient.getCommentEntities();
    }

    @Override
    public List<Comment> getCommentsByPostId(Long id) {

        var resultEntity = commentRepository.findById(id);

        var result = resultEntity.stream()
                .map(commentMapper::entityToDomain)
                .toList();
        return result;
    }


}
