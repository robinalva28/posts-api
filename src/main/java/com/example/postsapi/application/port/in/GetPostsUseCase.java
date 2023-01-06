package com.example.postsapi.application.port.in;

import com.example.postsapi.domain.Post;

import java.util.List;

public interface GetPostsUseCase {

    List<Post> getPosts(GetPostsCommand command);

    class GetPostsCommand {
        //        @NotNull(message = "ofset field cannot be null")
//        @Min(value = 1, message = "ofset field must be higer or equal to 1")
        Integer ofset;

        //        @NotNull(message = "limit field cannot be null")
//        @Min(value = 1, message = "limit field must be higer or equal to 1")
        Integer limit;

        public GetPostsCommand(Integer offset, Integer limit) {
            this.ofset = offset;
            this.limit = limit;
            //validateThis();
        }

        public Integer getOfset() {
            return ofset;
        }

        public void setOfset(Integer ofset) {
            this.ofset = ofset;
        }

        public Integer getLimit() {
            return limit;
        }

        public void setLimit(Integer limit) {
            this.limit = limit;
        }
    }

}
