package com.example.postsapi.adapter.in.web;

import com.example.postsapi.application.port.in.GetPostsUseCase;
import com.example.postsapi.domain.views.PostsView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController("/posts")
public class PostController {

    final Logger log = Logger.getLogger(PostController.class);
    final GetPostsUseCase getPostsUseCase;

    @Autowired
    public PostController(GetPostsUseCase getPostsUseCase) {
        this.getPostsUseCase = getPostsUseCase;
    }

    @GetMapping("/posts")
    @Operation(summary = "Posts with pagination", description = "Service that provides a post with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PostsView.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public PostsView getPosts(@RequestParam Integer offset, @RequestParam Integer limit) {

        log.info("call getPostUseCase...");
        var postList = getPostsUseCase.getPosts(new GetPostsUseCase.GetPostsCommand(offset, limit));

        return new PostsView(postList);
    }

}
