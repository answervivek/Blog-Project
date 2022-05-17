package com.vtripathi.blogproject.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

class CommentTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Comment#Comment()}
     *   <li>{@link Comment#setContent(String)}
     *   <li>{@link Comment#setId(int)}
     *   <li>{@link Comment#setPost(Post)}
     *   <li>{@link Comment#getContent()}
     *   <li>{@link Comment#getId()}
     *   <li>{@link Comment#getPost()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Comment actualComment = new Comment();
        actualComment.setContent("Not all who wander are lost");
        actualComment.setId(1);
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUsers(new ArrayList<>());
        Post post = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);
        actualComment.setPost(post);
        assertEquals("Not all who wander are lost", actualComment.getContent());
        assertEquals(1, actualComment.getId());
        assertSame(post, actualComment.getPost());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Comment#Comment(int, String, Post)}
     *   <li>{@link Comment#setContent(String)}
     *   <li>{@link Comment#setId(int)}
     *   <li>{@link Comment#setPost(Post)}
     *   <li>{@link Comment#getContent()}
     *   <li>{@link Comment#getId()}
     *   <li>{@link Comment#getPost()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUsers(new ArrayList<>());

        Post post = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);
        Comment actualComment = new Comment(1, "Not all who wander are lost", post);
        actualComment.setContent("Not all who wander are lost");
        actualComment.setId(1);
        Category category1 = new Category();
        category1.setCategoryDescription("Category Description");
        category1.setCategoryId(123);
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());
        User user1 = new User();
        user1.setAbout("About");
        user1.setEmail("jane.doe@example.org");
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setRoles(new HashSet<>());
        user1.setUsers(new ArrayList<>());
        Post post1 = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post1.setAddDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post1.setCategory(category1);
        post1.setComments(new HashSet<>());
        post1.setContent("Not all who wander are lost");
        post1.setImageName("Image Name");
        post1.setPostId(123);
        post1.setTitle("Dr");
        post1.setUser(user1);
        actualComment.setPost(post1);
        assertEquals("Not all who wander are lost", actualComment.getContent());
        assertEquals(1, actualComment.getId());
        assertSame(post1, actualComment.getPost());
    }
}

