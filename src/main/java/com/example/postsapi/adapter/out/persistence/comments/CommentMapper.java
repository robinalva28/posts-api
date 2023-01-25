package com.example.postsapi.adapter.out.persistence.comments;

import com.example.postsapi.domain.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper{
    Comment entityToDomain(CommentEntity entity);
}
