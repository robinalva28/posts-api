package com.example.postsapi.application.port.in;

import com.example.postsapi.common.validation.SelfValidate;
import com.example.postsapi.domain.Comment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetCommentByPostIdUseCase {

    List<Comment> getCommentByPostId(@Valid GetCommentByPostIdCommand command);

    class GetCommentByPostIdCommand extends SelfValidate<GetCommentByPostIdCommand> {

        @NotNull(message = "Post id wont be null")
        @Min(value = 0, message = "the post id must be higer than 0")
        Long postId;

        public GetCommentByPostIdCommand(Long postId) {
            this.postId = postId;
            validateThis();
        }

        public void setPostId(Long postId) {
            this.postId = postId;
        }

        public Long getPostId() {
            return postId;
        }
    }

}
