package com.example.postsapi.adapter.in.web;

import com.example.postsapi.application.port.in.GetPostsByTitleUseCase;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("v1/posts")
@Tag(name = "Posts")
public class SearchPostTitleController {

    final Logger log = Logger.getLogger(SearchPostTitleController.class);
    final GetPostsByTitleUseCase getPostsByTitleUseCase;

    @Autowired
    public SearchPostTitleController(GetPostsByTitleUseCase getPostsByTitleUseCase) {
        this.getPostsByTitleUseCase = getPostsByTitleUseCase;
    }

    @GetMapping("/search")
    @Operation(summary = "Post by title like", description = "Service that provides a post by its title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PostsView.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<PostsView> getPostsByTitle(@RequestParam String title) {
        log.info("GET /post/search -> getPostByTitleUseCase... with title: " + title);
        var result = getPostsByTitleUseCase.getPostsByTitle(new GetPostsByTitleUseCase.GetPostsByTitleCommand(title));

        log.info("GET /post/search -> getPostByTitleUseCase... with title: " + title +" DONE");
        return ResponseEntity.ok(new PostsView(result));
    }
}