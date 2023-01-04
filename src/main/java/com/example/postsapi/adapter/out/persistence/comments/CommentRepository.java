package com.example.postsapi.adapter.out.persistence.comments;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<CommentEntity, String> {

    CommentEntity getAllByBodyIsTrue();
}
