package com.example.postsapi.application.port.out;

import com.example.postsapi.adapter.out.client.PostEntitiesResponse;
import com.example.postsapi.domain.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostPort {
    List<PostEntitiesResponse> getAllPostsFromApi();

    Page<Post> getAllPosts(Integer page, Integer pageSize);

    Post getPostById(Long id);

    List<Post> getPostsByTitle(String title);
}