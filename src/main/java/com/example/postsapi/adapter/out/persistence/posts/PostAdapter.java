package com.example.postsapi.adapter.out.persistence.posts;

import com.example.postsapi.adapter.out.client.JsonPlaceHolderRestClient;
import com.example.postsapi.adapter.out.client.PostEntitiesResponse;
import com.example.postsapi.adapter.out.persistence.comments.CommentMapper;
import com.example.postsapi.adapter.out.persistence.comments.CommentRepository;
import com.example.postsapi.application.port.out.PostPort;
import com.example.postsapi.domain.Comment;
import com.example.postsapi.domain.Post;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostAdapter implements PostPort {

    final Logger log = Logger.getLogger(PostAdapter.class);

    private final JsonPlaceHolderRestClient jsonPlaceHolderRestClient;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostMapper postMapper;

    @Autowired
    public PostAdapter(JsonPlaceHolderRestClient jsonPlaceHolderRestClient, PostRepository postRepository, CommentRepository commentRepository, CommentMapper commentMapper, PostMapper postMapper) {
        this.jsonPlaceHolderRestClient = jsonPlaceHolderRestClient;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.postMapper = postMapper;
    }

    @Override
    public List<PostEntitiesResponse> getAllPostsFromApi() {
        log.debug("PostAdapter: requesting restClient...");
        return jsonPlaceHolderRestClient.getPostEntities();
    }

    @Override
    public Page<Post> getAllPosts(Integer page, Integer pageSize) {

        Pageable paging = PageRequest.of(page - 1, pageSize);

        var entitiesPage = this.postRepository.findAll(paging);

        return entitiesPage.map(entity -> {
            Post post = new Post();
            post.setId(entity.getId());
            post.setBody(entity.getBody());
            post.setTitle(entity.getTitle());
            post.setUserId(entity.getUserId());
            return post;
        });
    }

    @Override
    public Post getPostById(Long id) {
        var result = postRepository.findById(id).orElse(null);
        if (result == null) {
            return null;
        }
        return postMapper.entityToDomain(result);
    }

    @Override
    public List<Post> getPostsByTitle(String title) {
        var result = postRepository.findByTitleContainingIgnoreCase(title).orElse(null);

        if (result == null) {
            return null;
        }
        return result.stream()
                .map(postMapper::entityToDomain)
                .toList();
    }
    @Override
    public List<Comment> getCommentsByPostId(Long postId) {

        var resultEntity = commentRepository.findAllByPostId(postId);

        return resultEntity.stream()
                .map(commentMapper::entityToDomain)
                .toList();
    }
}
