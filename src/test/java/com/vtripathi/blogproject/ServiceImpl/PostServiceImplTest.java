package com.vtripathi.blogproject.ServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vtripathi.blogproject.Entity.Category;
import com.vtripathi.blogproject.Entity.Post;
import com.vtripathi.blogproject.Entity.User;
import com.vtripathi.blogproject.Exception.ResourceNotFoundException;
import com.vtripathi.blogproject.Payload.PostDto;
import com.vtripathi.blogproject.Repository.CategoryRepo;
import com.vtripathi.blogproject.Repository.PostRepo;
import com.vtripathi.blogproject.Repository.UserRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PostServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PostServiceImplTest {
    @MockBean
    private CategoryRepo categoryRepo;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PostRepo postRepo;

    @Autowired
    private PostServiceImpl postServiceImpl;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link PostServiceImpl#getPostByUserId(Integer)}
     */
    @Test
    void testGetPostByUserId() {
        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUsers(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepo.findById((Integer) any())).thenReturn(ofResult);
        when(this.postRepo.findByUser((User) any())).thenReturn(new ArrayList<>());
        assertTrue(this.postServiceImpl.getPostByUserId(123).isEmpty());
        verify(this.userRepo).findById((Integer) any());
        verify(this.postRepo).findByUser((User) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostByUserId(Integer)}
     */
    @Test
    void testGetPostByUserId2() {
        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUsers(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepo.findById((Integer) any())).thenReturn(ofResult);
        when(this.postRepo.findByUser((User) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));
        assertThrows(ResourceNotFoundException.class, () -> this.postServiceImpl.getPostByUserId(123));
        verify(this.userRepo).findById((Integer) any());
        verify(this.postRepo).findByUser((User) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostByUserId(Integer)}
     */
    @Test
    void testGetPostByUserId3() {
        when(this.userRepo.findById((Integer) any())).thenReturn(Optional.empty());
        when(this.postRepo.findByUser((User) any())).thenReturn(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class, () -> this.postServiceImpl.getPostByUserId(123));
        verify(this.userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostByUserId(Integer)}
     */
    @Test
    void testGetPostByUserId4() {
        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUsers(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepo.findById((Integer) any())).thenReturn(ofResult);

        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user1 = new User();
        user1.setAbout("About");
        user1.setEmail("jane.doe@example.org");
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setRoles(new HashSet<>());
        user1.setUsers(new ArrayList<>());

        Post post = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user1);

        ArrayList<Post> postList = new ArrayList<>();
        postList.add(post);
        when(this.postRepo.findByUser((User) any())).thenReturn(postList);
        when(this.modelMapper.map((Object) any(), (Class<PostDto>) any())).thenReturn(new PostDto());
        assertEquals(1, this.postServiceImpl.getPostByUserId(123).size());
        verify(this.userRepo).findById((Integer) any());
        verify(this.postRepo).findByUser((User) any());
        verify(this.modelMapper).map((Object) any(), (Class<PostDto>) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostByUserId(Integer)}
     */
    @Test
    void testGetPostByUserId5() {
        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUsers(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepo.findById((Integer) any())).thenReturn(ofResult);

        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user1 = new User();
        user1.setAbout("About");
        user1.setEmail("jane.doe@example.org");
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setRoles(new HashSet<>());
        user1.setUsers(new ArrayList<>());

        Post post = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user1);

        Category category1 = new Category();
        category1.setCategoryDescription("Category Description");
        category1.setCategoryId(123);
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        User user2 = new User();
        user2.setAbout("About");
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setRoles(new HashSet<>());
        user2.setUsers(new ArrayList<>());

        Post post1 = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post1.setAddDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post1.setCategory(category1);
        post1.setComments(new HashSet<>());
        post1.setContent("Not all who wander are lost");
        post1.setImageName("Image Name");
        post1.setPostId(123);
        post1.setTitle("Dr");
        post1.setUser(user2);

        ArrayList<Post> postList = new ArrayList<>();
        postList.add(post1);
        postList.add(post);
        when(this.postRepo.findByUser((User) any())).thenReturn(postList);
        when(this.modelMapper.map((Object) any(), (Class<PostDto>) any())).thenReturn(new PostDto());
        assertEquals(2, this.postServiceImpl.getPostByUserId(123).size());
        verify(this.userRepo).findById((Integer) any());
        verify(this.postRepo).findByUser((User) any());
        verify(this.modelMapper, atLeast(1)).map((Object) any(), (Class<PostDto>) any());
    }
}

