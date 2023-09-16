package com.example.postsapi.application.port.in;

import com.example.postsapi.common.validation.SelfValidate;
import com.example.postsapi.domain.Post;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface GetPostsUseCase {

    Page<Post> getPosts(@Valid GetPostsCommand command);

    class GetPostsCommand extends SelfValidate<GetPostsCommand> {
        @NotNull(message = "page field cannot be null")
        @Min(value = 1, message = "pageField must be higher or equal to 1")
        Integer page;

        @NotNull(message = "pageSize field cannot be null")
        @Min(value = 2, message = "pageSize field must be higher or equal to 2")
        Integer pageSize;

        public GetPostsCommand(Integer page, Integer pageSize) {
            this.page = page;
            this.pageSize = pageSize;
            validateThis();
        }

        public Integer getPage() {
            return page;
        }

        public Integer getPageSize() {
            return pageSize;
        }
    }
}
