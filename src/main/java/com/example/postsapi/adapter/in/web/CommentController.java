package com.example.postsapi.adapter.in.web;


import com.example.postsapi.application.port.in.GetCommentByPostIdUseCase;
import com.example.postsapi.common.exception.GenericError;
import com.example.postsapi.domain.views.CommentsView;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("v1/comments")
@RestController
@Tag(name = "Comments")
public class CommentController {

    final Logger log = Logger.getLogger(CommentController.class);

    GetCommentByPostIdUseCase getCommentByPostIdUseCase;

    @Autowired
    public CommentController(GetCommentByPostIdUseCase getCommentByPostIdUseCase) {
        this.getCommentByPostIdUseCase = getCommentByPostIdUseCase;
    }

    @GetMapping("/{postId}")
    @Operation(summary = "Comments by post id", description = "Service that provides a comment by its postId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CommentsView.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenericError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenericError.class)))
    })
    public ResponseEntity<List<CommentsView>> getCommentsByPostId(@PathVariable(value = "postId", required = true) Long postId) {
        log.info("call GET /comment/{postId} -> getCommentByPostIdUseCase...");

        GetCommentByPostIdUseCase.GetCommentByPostIdCommand command = new GetCommentByPostIdUseCase.GetCommentByPostIdCommand(postId);

        var view = getCommentByPostIdUseCase.getCommentByPostId(command).stream()
                .map(c -> new CommentsView(c.getId(), c.getName(), c.getEmail(), c.getBody()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(view);
    }

}
