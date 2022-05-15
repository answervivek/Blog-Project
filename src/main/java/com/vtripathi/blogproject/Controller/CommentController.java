package com.vtripathi.blogproject.Controller;

import com.vtripathi.blogproject.Payload.ApiResponse;
import com.vtripathi.blogproject.Payload.CommentDto;
import com.vtripathi.blogproject.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    ResponseEntity<CommentDto> commentComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {
        CommentDto createdComment = this.commentService.createComment(commentDto, postId);
        return new ResponseEntity<CommentDto>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("comments/{commentId}")
    ResponseEntity<ApiResponse> deleteComments(@PathVariable Integer commentId) {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully", true), HttpStatus.OK);
    }

}
