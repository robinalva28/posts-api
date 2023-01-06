package com.example.postsapi.adapter.out.persistence.posts;

import com.example.postsapi.domain.Post;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PostMapper {
    Post entityToDomain(PostEntity entity);
}
