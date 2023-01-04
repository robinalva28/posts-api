package com.example.postsapi.adapter.out.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentEntitiesResponse {
@JsonProperty("id")
    private Long id;
@JsonProperty("name")
    private String name;
@JsonProperty("email")
    private String email;
@JsonProperty("body")
    private String body;
@JsonProperty("postId")
    private Long postId;
}
