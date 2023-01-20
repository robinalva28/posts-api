package com.example.postsapi.adapter.out.persistence.posts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Optional<List<PostEntity>> findByTitleContainingIgnoreCase(String title);
}
