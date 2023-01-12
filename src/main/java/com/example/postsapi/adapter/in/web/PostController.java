package com.example.postsapi.adapter.in.web;

import com.example.postsapi.application.port.in.GetPostByIdUseCase;
import com.example.postsapi.application.port.in.GetPostsByTitleUseCase;
import com.example.postsapi.application.port.in.GetPostsUseCase;
import com.example.postsapi.domain.Post;
import com.example.postsapi.domain.views.PostsView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController("/posts")
@Tag(name = "Posts")
public class PostController {

    final Logger log = Logger.getLogger(PostController.class);
    final GetPostsUseCase getPostsUseCase;
    final GetPostByIdUseCase getPostByIdUseCase;
    final GetPostsByTitleUseCase getPostsByTitleUseCase;

    @Autowired
    public PostController(GetPostsUseCase getPostsUseCase, GetPostByIdUseCase getPostByIdUseCase, GetPostsByTitleUseCase getPostsByTitleUseCase) {
        this.getPostsUseCase = getPostsUseCase;
        this.getPostByIdUseCase = getPostByIdUseCase;
        this.getPostsByTitleUseCase = getPostsByTitleUseCase;
    }

    @GetMapping("/posts")
    @Operation(summary = "Posts with pagination", description = "Service that provides a post with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PostsView.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<PostsView> getPosts(@RequestParam(value = "offset") Integer offset,
                                              @RequestParam(value = "limit") Integer limit
    ) {

        log.info("call GET /posts -> getPostUseCase...");
        var postList = getPostsUseCase.getPosts(new GetPostsUseCase.GetPostsCommand(offset, limit));

        var view = new PostsView(postList);

        return ResponseEntity.ok(view);
    }

    @GetMapping("/posts/{id}")
    @Operation(summary = "Post by post id", description = "Service that provides a post by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PostsView.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<Post> getPostsById(@PathVariable("id") Long id) {
        log.info("call GET /post/id -> getPostByIdUseCase...");

        GetPostByIdUseCase.GetPostByIdCommand command = new GetPostByIdUseCase.GetPostByIdCommand(id);

        var view = getPostByIdUseCase.getPostById(command);

        return ResponseEntity.ok(view);
    }

    @GetMapping("/posts/search")
    @Operation(summary = "Post by title like", description = "Service that provides a post by its title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PostsView.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<PostsView> getPostsByTitle(@RequestParam String title) {
        log.info("call GET /post/search -> getPostByTitleUseCase...");
        var result = getPostsByTitleUseCase.getPostsByTitle(new GetPostsByTitleUseCase.GetPostsByTitleCommand(title));

        return ResponseEntity.ok(new PostsView(result));
    }
}
