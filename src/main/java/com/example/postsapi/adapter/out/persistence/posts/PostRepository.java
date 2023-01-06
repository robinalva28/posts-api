package com.example.postsapi.adapter.out.persistence.posts;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends MongoRepository<PostEntity, Long> {

    @Query("{title: {$eq:'?0'}}")
    Optional<List<PostEntity>> getByTitle(String title);

}
