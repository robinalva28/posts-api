package com.example.postsapi.application.port.out;

import com.example.postsapi.adapter.out.client.PostEntitiesResponse;
import com.example.postsapi.adapter.out.persistence.posts.PostEntity;

import java.util.List;

public interface PostPort {
    List<PostEntitiesResponse> getAllPostsFromApi();

    List<PostEntity> getAllPosts();
}
