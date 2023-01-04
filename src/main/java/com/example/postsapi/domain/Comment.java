package com.example.postsapi.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Data model for post's comments")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private String name;
    private String email;
    private String body;
    private Long postId;
}
