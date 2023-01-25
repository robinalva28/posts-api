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


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getId() {
        return id;
    }
}
