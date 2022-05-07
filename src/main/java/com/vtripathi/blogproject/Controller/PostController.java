package com.vtripathi.blogproject.Controller;

import com.vtripathi.blogproject.Config.AppConstants;
import com.vtripathi.blogproject.Payload.ApiResponse;
import com.vtripathi.blogproject.Payload.PostDto;
import com.vtripathi.blogproject.Payload.PostResponse;
import com.vtripathi.blogproject.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    @PutMapping("post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Integer userId) {
        List<PostDto> posts = this.postService.getPostByUserId(userId);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable Integer categoryId) {
        List<PostDto> posts = this.postService.getPostByCategoryId(categoryId);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto post = this.postService.getPostById(postId);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("posts/")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
        PostResponse postResponse = this.postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }


    @DeleteMapping("post/{postId}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping("posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword) {
        List<PostDto> posts = this.postService.searchPost(keyword);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
