package com.example.postsapi.adapter.out.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostEntitiesResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Long getId() {
        return id;
    }
}
