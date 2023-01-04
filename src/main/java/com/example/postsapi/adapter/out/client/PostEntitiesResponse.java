package com.example.postsapi.adapter.out.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostEntitiesResponse {
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;

}
