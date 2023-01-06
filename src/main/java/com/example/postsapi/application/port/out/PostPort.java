package com.example.postsapi.application.port.out;

import com.example.postsapi.adapter.out.client.PostEntitiesResponse;
import com.example.postsapi.domain.Post;

import java.util.List;

public interface PostPort {
    List<PostEntitiesResponse> getAllPostsFromApi();

    List<Post> getAllPosts(Integer offset, Integer limit);

    Post getPostById(Long id);

    List<Post> getPostsByTitle(String title);
}