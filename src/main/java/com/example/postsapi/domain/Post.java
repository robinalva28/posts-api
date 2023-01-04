package com.example.postsapi.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Data model for posts")
@Setter
@Getter
public class Post {
    private Long id;
    private String title;
    private String body;
    private Long userId;

    public Post(Long id, String title, String body, Long userId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
}
