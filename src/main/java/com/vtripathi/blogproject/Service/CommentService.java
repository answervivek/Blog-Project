package com.vtripathi.blogproject.Service;

import com.vtripathi.blogproject.Payload.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer commentId);
}
