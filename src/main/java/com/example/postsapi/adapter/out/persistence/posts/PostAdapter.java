package com.example.postsapi.adapter.out.persistence.posts;

import com.example.postsapi.adapter.out.client.JsonPlaceHolderRestClient;
import com.example.postsapi.adapter.out.client.PostEntitiesResponse;
import com.example.postsapi.application.port.out.PostPort;
import com.example.postsapi.domain.Post;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostAdapter implements PostPort {

    final Logger log = Logger.getLogger(PostAdapter.class);

    private final JsonPlaceHolderRestClient jsonPlaceHolderRestClient;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostAdapter(JsonPlaceHolderRestClient jsonPlaceHolderRestClient, PostRepository postRepository, PostMapper postMapper) {
        this.jsonPlaceHolderRestClient = jsonPlaceHolderRestClient;
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public List<PostEntitiesResponse> getAllPostsFromApi() {
        log.info("PostAdapter: requesting restClient...");
        return jsonPlaceHolderRestClient.getPostEntities();
    }

    @Override
    public List<Post> getAllPosts(Integer offset, Integer limit) {
        log.info("PostAdapter: requesting findAll...");
        var response = postRepository.findAll().stream().skip(offset).limit(limit).toList();
        if (response.isEmpty()) {
            return List.of();
        }
        return response.stream()
                .map(postMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Post getPostById(Long id) {
        var result = postRepository.findById(id).orElse(null);
        if(result == null){
            return null;
        }
        return postMapper.entityToDomain(result);
    }

    @Override
    public List<Post> getPostsByTitle(String title) {
        var result = postRepository.getByTitle(title).orElse(null);

        if(result == null){
            return null;
        }

        return result.stream()
                .map(postMapper::entityToDomain)
                .toList();
    }
}
