package com.example.postsapi.adapter.in.web;

import com.example.postsapi.application.port.in.GetPostsUseCase;
import com.example.postsapi.application.port.in.GetPostsUseCase.GetPostsCommand;
import com.example.postsapi.domain.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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

import java.util.List;

@RestController
@RequestMapping("v1/posts")
@Tag(name = "Posts")
public class GetAllPostsController {

    final Logger log = Logger.getLogger(GetAllPostsController.class);

    final GetPostsUseCase getPostsUseCase;

    @Autowired
    public GetAllPostsController(GetPostsUseCase getPostsUseCase) {
        this.getPostsUseCase = getPostsUseCase;
    }

    @GetMapping
    @Operation(summary = "Get all Posts", description = "Service that provides all posts paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<List<Post>> getPosts(@RequestParam(defaultValue = "1") Integer page,
                                               @RequestParam(defaultValue = "10") Integer pageSize
    ) {

        log.debug("GET /posts -> getPostsUseCase...");
        var postList = getPostsUseCase.getPosts(new GetPostsCommand(page, pageSize));

        log.debug("GET /posts -> getPostsUseCase... DONE");
        return ResponseEntity.ok(postList.getContent());
    }
}