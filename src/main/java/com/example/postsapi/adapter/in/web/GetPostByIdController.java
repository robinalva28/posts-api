package com.example.postsapi.adapter.in.web;

import com.example.postsapi.application.port.in.GetPostByIdUseCase;
import com.example.postsapi.application.port.in.GetPostByIdUseCase.GetPostByIdCommand;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/posts")
@Tag(name = "Posts")
public class GetPostByIdController {

    final Logger log = Logger.getLogger(GetPostByIdController.class);

    final GetPostByIdUseCase getPostByIdUseCase;

    @Autowired
    public GetPostByIdController(GetPostByIdUseCase getPostByIdUseCase) {
        this.getPostByIdUseCase = getPostByIdUseCase;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Post by id", description = "Service that provides a post by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<Post> getPostsById(@PathVariable("id") Long id) {
        log.debug("GET /post/{"+id+"} -> getPostByIdUseCase...");

        GetPostByIdCommand command = new GetPostByIdCommand(id);

        var view = getPostByIdUseCase.getPostById(command);

        log.debug("GET /post/{"+id+"} -> getPostByIdUseCase... DONE");
        return ResponseEntity.ok(view);
    }
}