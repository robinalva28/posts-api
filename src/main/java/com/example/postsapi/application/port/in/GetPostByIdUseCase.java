package com.example.postsapi.application.port.in;

import com.example.postsapi.common.validation.SelfValidate;
import com.example.postsapi.domain.Post;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public interface GetPostByIdUseCase {
    Post getPostById(@Valid GetPostByIdCommand command);

    class GetPostByIdCommand extends SelfValidate<GetPostsUseCase.GetPostsCommand> {

        @NotNull
        @Min(value = 1, message = "the id post must be higher than 1")
        Long id;

        public GetPostByIdCommand(Long id) {
            this.id = id;
            validateThis();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
