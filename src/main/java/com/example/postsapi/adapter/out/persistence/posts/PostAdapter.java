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

    @Autowired
    public PostAdapter(JsonPlaceHolderRestClient jsonPlaceHolderRestClient) {
        this.jsonPlaceHolderRestClient = jsonPlaceHolderRestClient;
    }

    @Override
    public List<PostEntitiesResponse> getAllPosts() {
        log.info("Adapter: requesting restClient...");
        return jsonPlaceHolderRestClient.getPostEntities();
    }
}
