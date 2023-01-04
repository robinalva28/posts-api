package com.example.postsapi.adapter.out.persistence.posts;

import com.example.postsapi.adapter.out.client.JsonPlaceHolderRestClient;
import com.example.postsapi.adapter.out.client.PostEntitiesResponse;
import com.example.postsapi.application.port.out.PostPort;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostAdapter implements PostPort {

    final Logger log = Logger.getLogger(PostAdapter.class);

    private final JsonPlaceHolderRestClient jsonPlaceHolderRestClient;
    private final PostRepository postRepository;

    @Autowired
    public PostAdapter(JsonPlaceHolderRestClient jsonPlaceHolderRestClient, PostRepository postRepository) {
        this.jsonPlaceHolderRestClient = jsonPlaceHolderRestClient;
        this.postRepository = postRepository;
    }

    @Override
    public List<PostEntitiesResponse> getAllPostsFromApi() {
        log.info("Adapter: requesting restClient...");
        return jsonPlaceHolderRestClient.getPostEntities();
    }

    @Override
    public List<PostEntity> getAllPosts() {
        //TODO Obtain all posts from db, map to domain object and return to the view, then apply pagination
        var response = postRepository.getAllByBodyIsTrue();
        return List.of();
    }
}
