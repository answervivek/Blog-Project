package com.vtripathi.blogproject.Controller;

import com.vtripathi.blogproject.Config.AppConstants;
import com.vtripathi.blogproject.Payload.ApiResponse;
import com.vtripathi.blogproject.Payload.PostDto;
import com.vtripathi.blogproject.Payload.PostResponse;
import com.vtripathi.blogproject.Service.FileService;
import com.vtripathi.blogproject.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

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
    //Get All Post
    @GetMapping("posts/")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
        PostResponse postResponse = this.postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    //Get Post By PostId
    @DeleteMapping("post/{postId}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
    }
    //Get Post By Search
    @GetMapping("posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword) {
        List<PostDto> posts = this.postService.searchPost(keyword);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //Post Image Upload
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDto updatedPost = this.postService.updatePost(postDto, postId);

        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
    }

    //Method to Serve Image
    @GetMapping(value = "post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {

        InputStream resourse = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resourse, response.getOutputStream());
    }
}