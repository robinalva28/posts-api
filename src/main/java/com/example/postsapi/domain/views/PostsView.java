package com.example.postsapi.domain.views;

import com.example.postsapi.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Data model for posts")
public class PostsView {

    @Schema(description = "List of posts")
    private final List<Post> postList;

    public PostsView(List<Post> postList) {
        this.postList = postList;
    }

    public List<Post> getPosts() {
        return postList;
    }
}
