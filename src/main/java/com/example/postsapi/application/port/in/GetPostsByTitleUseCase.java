package com.example.postsapi.application.port.in;

import com.example.postsapi.domain.Post;

import java.util.List;

public interface GetPostsByTitleUseCase {
    List<Post> getPostsByTitle(GetPostsByTitleCommand command);

    class GetPostsByTitleCommand{

        String title;

        public GetPostsByTitleCommand(String title) {
            this.title = title;
            //validateSelf()
        }

        public String getTitle() {
            return title;
        }
    }
}
