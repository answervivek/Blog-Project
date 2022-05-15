package com.vtripathi.blogproject.ServiceImpl;

import com.vtripathi.blogproject.Entity.Comment;
import com.vtripathi.blogproject.Entity.Post;
import com.vtripathi.blogproject.Exception.ResourceNotFoundException;
import com.vtripathi.blogproject.Payload.CommentDto;
import com.vtripathi.blogproject.Repository.CommentRepo;
import com.vtripathi.blogproject.Repository.PostRepo;
import com.vtripathi.blogproject.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        Comment comments= dtoToComment(commentDto);
        comments.setPost(post);
        Comment savedComments= this.commentRepo.save(comments);
        return this.commentToDto(savedComments);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment= this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));
        this.commentRepo.delete(comment);
    }

    public Comment dtoToComment(CommentDto commentDto){
        return modelMapper.map(commentDto, Comment.class);
    }

    public CommentDto commentToDto (Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }
}
