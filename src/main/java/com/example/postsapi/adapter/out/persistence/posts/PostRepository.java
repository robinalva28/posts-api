package com.example.postsapi.adapter.out.persistence.posts;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<PostEntity, String> {

    PostEntity getAllByBodyIsTrue();

}
