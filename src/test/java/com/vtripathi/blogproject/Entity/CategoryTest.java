package com.vtripathi.blogproject.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CategoryTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Category#Category()}
     *   <li>{@link Category#setCategoryDescription(String)}
     *   <li>{@link Category#setCategoryId(Integer)}
     *   <li>{@link Category#setCategoryTitle(String)}
     *   <li>{@link Category#setPosts(List)}
     *   <li>{@link Category#getCategoryDescription()}
     *   <li>{@link Category#getCategoryId()}
     *   <li>{@link Category#getCategoryTitle()}
     *   <li>{@link Category#getPosts()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Category actualCategory = new Category();
        actualCategory.setCategoryDescription("Category Description");
        actualCategory.setCategoryId(123);
        actualCategory.setCategoryTitle("Dr");
        ArrayList<Post> postList = new ArrayList<>();
        actualCategory.setPosts(postList);
        assertEquals("Category Description", actualCategory.getCategoryDescription());
        assertEquals(123, actualCategory.getCategoryId().intValue());
        assertEquals("Dr", actualCategory.getCategoryTitle());
        assertSame(postList, actualCategory.getPosts());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Category#Category(Integer, String, String, List)}
     *   <li>{@link Category#setCategoryDescription(String)}
     *   <li>{@link Category#setCategoryId(Integer)}
     *   <li>{@link Category#setCategoryTitle(String)}
     *   <li>{@link Category#setPosts(List)}
     *   <li>{@link Category#getCategoryDescription()}
     *   <li>{@link Category#getCategoryId()}
     *   <li>{@link Category#getCategoryTitle()}
     *   <li>{@link Category#getPosts()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        ArrayList<Post> postList = new ArrayList<>();
        Category actualCategory = new Category(123, "Dr", "Category Description", postList);
        actualCategory.setCategoryDescription("Category Description");
        actualCategory.setCategoryId(123);
        actualCategory.setCategoryTitle("Dr");
        ArrayList<Post> postList1 = new ArrayList<>();
        actualCategory.setPosts(postList1);
        assertEquals("Category Description", actualCategory.getCategoryDescription());
        assertEquals(123, actualCategory.getCategoryId().intValue());
        assertEquals("Dr", actualCategory.getCategoryTitle());
        List<Post> posts = actualCategory.getPosts();
        assertSame(postList1, posts);
        assertEquals(postList, posts);
    }
}

