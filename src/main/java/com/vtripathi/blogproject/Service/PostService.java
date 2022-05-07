package com.vtripathi.blogproject.Service;

import com.vtripathi.blogproject.Entity.Post;
import com.vtripathi.blogproject.Payload.PostDto;
import com.vtripathi.blogproject.Payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostByUserId(Integer userId);

    List<PostDto> getPostByCategoryId(Integer categoryId);

    List<PostDto> searchPost(String keyword);
}
