package com.example.postsapi.application.port.in;

import com.example.postsapi.domain.Post;

import java.util.List;

public interface GetPostByIdUseCase {
    Post getPostById(GetPostByIdCommand command);

    class GetPostByIdCommand {

        //        @NotNull
//        @Min(1)
        Long id;

        public GetPostByIdCommand(Long id) {
            this.id = id;
//            validateSelf()
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
