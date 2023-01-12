package com.example.postsapi.application.port.in;

import com.example.postsapi.common.validation.SelfValidate;
import com.example.postsapi.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public interface GetPostsByTitleUseCase {
    List<Post> getPostsByTitle(GetPostsByTitleCommand command);

    class GetPostsByTitleCommand extends SelfValidate<GetPostsByTitleCommand> {

        @NotBlank(message = "title won't be blank")
        @Pattern(regexp = "^[A-Za-z\\s]+$", message = "title must have letters and spaces only")
        String title;

        public GetPostsByTitleCommand(String title) {
            this.title = title;
            validateThis();
        }

        public String getTitle() {
            return title;
        }
    }
}
