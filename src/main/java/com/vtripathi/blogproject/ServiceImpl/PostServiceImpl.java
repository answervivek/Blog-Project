package com.vtripathi.blogproject.ServiceImpl;

import com.vtripathi.blogproject.Entity.Category;
import com.vtripathi.blogproject.Entity.Post;
import com.vtripathi.blogproject.Entity.User;
import com.vtripathi.blogproject.Exception.ResourceNotFoundException;
import com.vtripathi.blogproject.Payload.PostDto;
import com.vtripathi.blogproject.Payload.PostResponse;
import com.vtripathi.blogproject.Repository.CategoryRepo;
import com.vtripathi.blogproject.Repository.PostRepo;
import com.vtripathi.blogproject.Repository.UserRepo;
import com.vtripathi.blogproject.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow((() -> new ResourceNotFoundException("User", "id", userId)));

        Category category = this.categoryRepo.findById(categoryId).orElseThrow((() -> new ResourceNotFoundException("Category", "id", categoryId)));

        Post post = dtoToPost(postDto);
        post.setImageName("default.jpg");
        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savePost = this.postRepo.save(post);

        return this.postToDto(savePost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        post.setTitle(postDto.getTitle());
        post.setImageName(postDto.getImageName());
        post.setContent(postDto.getContent());
        Post savePost = this.postRepo.save(post);

        return this.postToDto(savePost);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending():
                (sortDir.equalsIgnoreCase("desc"))?Sort.by(sortBy).descending(): Sort.by(sortBy);

//        if(sortDir.equalsIgnoreCase("asc")){
//            sort = Sort.by(sortBy).ascending();
//        } else if (sortDir.equalsIgnoreCase("desc")) {
//            sort = Sort.by(sortBy).descending();
//        }

        Pageable paging = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> pagePosts = this.postRepo.findAll(paging);
        List<Post> posts = pagePosts.getContent();
        List<PostDto> postDto = posts.stream().map(this::postToDto).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDto);
        postResponse.setPageNo(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setLastPage(pagePosts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
        return this.postToDto(post);
    }

    @Override
    public List<PostDto> getPostByUserId(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        return posts.stream().map(this::postToDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByCategoryId(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        return posts.stream().map(this::postToDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        return posts.stream().map(this::postToDto).collect(Collectors.toList());
    }

    public Post dtoToPost(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }

    public PostDto postToDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }
}
