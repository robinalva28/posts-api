package com.example.postsapi.adapter.out.persistence.comments;

import com.example.postsapi.adapter.out.client.JsonPlaceHolderRestClient;
import com.example.postsapi.application.port.out.CommentPort;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentAdapter implements CommentPort {

    final Logger log = Logger.getLogger(com.example.postsapi.adapter.out.persistence.posts.PostAdapter.class);

    private final JsonPlaceHolderRestClient jsonPlaceHolderRestClient;

    @Autowired
    public CommentAdapter(JsonPlaceHolderRestClient jsonPlaceHolderRestClient) {
        this.jsonPlaceHolderRestClient = jsonPlaceHolderRestClient;
    }

    @Override
    public Object getAllcomments() {
        log.info("Adapter: requesting restClient...");
        return jsonPlaceHolderRestClient.getPostEntities();
    }
}
