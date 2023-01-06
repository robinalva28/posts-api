package com.example.postsapi.adapter.in.web;

import com.example.postsapi.application.port.in.GetPostByIdUseCase;
import com.example.postsapi.application.port.in.GetPostsUseCase;
import com.example.postsapi.domain.Post;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController("/posts")
public class PostController {

    final Logger log = Logger.getLogger(PostController.class);
    final GetPostsUseCase getPostsUseCase;
    final GetPostByIdUseCase getPostByIdUseCase;

    @Autowired
    public PostController(GetPostsUseCase getPostsUseCase, GetPostByIdUseCase getPostByIdUseCase) {
        this.getPostsUseCase = getPostsUseCase;
        this.getPostByIdUseCase = getPostByIdUseCase;
    }

    @GetMapping("/posts")
    @Operation(summary = "Posts with pagination", description = "Service that provides a post with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PostsView.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public PostsView getPosts(@RequestParam Integer offset, @RequestParam Integer limit) {

        log.info("call GET /posts -> getPostUseCase...");
        var postList = getPostsUseCase.getPosts(new GetPostsUseCase.GetPostsCommand(offset, limit));

        return new PostsView(postList);
    }

    @GetMapping("/posts/{id}")
    @Operation(summary = "Post by post id", description = "Service that provides a post by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PostsView.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Post getPostsById(@PathVariable("id")Long id){
        log.info("call GET /post/id -> getPostByIdUseCase...");

        GetPostByIdUseCase.GetPostByIdCommand command = new GetPostByIdUseCase.GetPostByIdCommand(id);

        return getPostByIdUseCase.getPostById(command);
    }

}
