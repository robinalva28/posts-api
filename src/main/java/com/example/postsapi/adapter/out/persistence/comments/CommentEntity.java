package com.example.postsapi.adapter.out.persistence.comments;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentEntity {
    private String name;
    private String email;
    private String body;
    private Long postId;
    private LocalDateTime created;

    public CommentEntity(String name, String email, String body, Long postId, LocalDateTime created) {
        this.name = name;
        this.email = email;
        this.body = body;
        this.postId = postId;
        this.created = created;
    }
}
