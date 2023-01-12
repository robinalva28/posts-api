package com.example.postsapi.application.port.in;

import com.example.postsapi.common.validation.SelfValidate;
import com.example.postsapi.domain.Post;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface GetPostsUseCase {

    List<Post> getPosts(@Valid GetPostsCommand command);

    class GetPostsCommand extends SelfValidate<GetPostsCommand> {
        @NotNull(message = "offset field cannot be null")
        @Min(value = 0, message = "offset field must be higher or equal to 0")
        Integer offset;

        @NotNull(message = "limit field cannot be null")
        @Min(value = 1, message = "limit field must be higher or equal to 1")
        Integer limit;

        public GetPostsCommand(Integer offset, Integer limit) {
            this.offset = offset;
            this.limit = limit;
            validateThis();
        }

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        public Integer getLimit() {
            return limit;
        }

        public void setLimit(Integer limit) {
            this.limit = limit;
        }
    }

}
