package com.example.postsapi.application.port.out;

import com.example.postsapi.adapter.out.client.PostEntitiesResponse;

import java.util.List;

public interface PostPort {
    List<PostEntitiesResponse> getAllPosts();
}
