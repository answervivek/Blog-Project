package com.vtripathi.blogproject.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class PostTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Post#Post()}
     *   <li>{@link Post#setAddDate(Date)}
     *   <li>{@link Post#setCategory(Category)}
     *   <li>{@link Post#setComments(Set)}
     *   <li>{@link Post#setContent(String)}
     *   <li>{@link Post#setImageName(String)}
     *   <li>{@link Post#setPostId(Integer)}
     *   <li>{@link Post#setTitle(String)}
     *   <li>{@link Post#setUser(User)}
     *   <li>{@link Post#getAddDate()}
     *   <li>{@link Post#getCategory()}
     *   <li>{@link Post#getComments()}
     *   <li>{@link Post#getContent()}
     *   <li>{@link Post#getImageName()}
     *   <li>{@link Post#getPostId()}
     *   <li>{@link Post#getTitle()}
     *   <li>{@link Post#getUser()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Post actualPost = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        actualPost.setAddDate(fromResult);
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        actualPost.setCategory(category);
        HashSet<Comment> commentSet = new HashSet<>();
        actualPost.setComments(commentSet);
        actualPost.setContent("Not all who wander are lost");
        actualPost.setImageName("Image Name");
        actualPost.setPostId(123);
        actualPost.setTitle("Dr");
        User user = new User();
        user.setAbout("About");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUsers(new ArrayList<>());
        actualPost.setUser(user);
        assertSame(fromResult, actualPost.getAddDate());
        assertSame(category, actualPost.getCategory());
        assertSame(commentSet, actualPost.getComments());
        assertEquals("Not all who wander are lost", actualPost.getContent());
        assertEquals("Image Name", actualPost.getImageName());
        assertEquals(123, actualPost.getPostId().intValue());
        assertEquals("Dr", actualPost.getTitle());
        assertSame(user, actualPost.getUser());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Post#Post(Integer, String, String, String, Date, Category, User, Set)}
     *   <li>{@link Post#setAddDate(Date)}
     *   <li>{@link Post#setCategory(Category)}
     *   <li>{@link Post#setComments(Set)}
     *   <li>{@link Post#setContent(String)}
     *   <li>{@link Post#setImageName(String)}
     *   <li>{@link Post#setPostId(Integer)}
     *   <li>{@link Post#setTitle(String)}
     *   <li>{@link Post#setUser(User)}
     *   <li>{@link Post#getAddDate()}
     *   <li>{@link Post#getCategory()}
     *   <li>{@link Post#getComments()}
     *   <li>{@link Post#getContent()}
     *   <li>{@link Post#getImageName()}
     *   <li>{@link Post#getPostId()}
     *   <li>{@link Post#getTitle()}
     *   <li>{@link Post#getUser()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date addDate = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());

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
        Post actualPost = new Post(123, "Dr", "Not all who wander are lost", "Image Name", addDate, category, user,
                new HashSet<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        actualPost.setAddDate(fromResult);
        Category category1 = new Category();
        category1.setCategoryDescription("Category Description");
        category1.setCategoryId(123);
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());
        actualPost.setCategory(category1);
        HashSet<Comment> commentSet = new HashSet<>();
        actualPost.setComments(commentSet);
        actualPost.setContent("Not all who wander are lost");
        actualPost.setImageName("Image Name");
        actualPost.setPostId(123);
        actualPost.setTitle("Dr");
        User user1 = new User();
        user1.setAbout("About");
        user1.setEmail("jane.doe@example.org");
        user1.setId(1);
        user1.setName("Name");
        user1.setPassword("iloveindia");
        user1.setRoles(new HashSet<>());
        user1.setUsers(new ArrayList<>());
        actualPost.setUser(user1);
        assertSame(fromResult, actualPost.getAddDate());
        assertSame(category1, actualPost.getCategory());
        assertSame(commentSet, actualPost.getComments());
        assertEquals("Not all who wander are lost", actualPost.getContent());
        assertEquals("Image Name", actualPost.getImageName());
        assertEquals(123, actualPost.getPostId().intValue());
        assertEquals("Dr", actualPost.getTitle());
        assertSame(user1, actualPost.getUser());
    }
}

