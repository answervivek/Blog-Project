package com.vtripathi.blogproject.ServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vtripathi.blogproject.Entity.Category;
import com.vtripathi.blogproject.Entity.Post;
import com.vtripathi.blogproject.Exception.ResourceNotFoundException;
import com.vtripathi.blogproject.Repository.CategoryRepo;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CategoryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {
    @MockBean
    private CategoryRepo categoryRepo;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(Integer)}
     */
    @Test
    void testDeleteCategory() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        ArrayList<Post> postList = new ArrayList<>();
        category.setPosts(postList);
        Optional<Category> ofResult = Optional.of(category);
        doNothing().when(this.categoryRepo).delete((Category) any());
        when(this.categoryRepo.findById((Integer) any())).thenReturn(ofResult);
        this.categoryServiceImpl.deleteCategory(123);
        verify(this.categoryRepo).findById((Integer) any());
        verify(this.categoryRepo).delete((Category) any());
        assertEquals(postList, this.categoryServiceImpl.getAllCategories());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(Integer)}
     */
    @Test
    void testDeleteCategory2() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42)).when(this.categoryRepo)
                .delete((Category) any());
        when(this.categoryRepo.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> this.categoryServiceImpl.deleteCategory(123));
        verify(this.categoryRepo).findById((Integer) any());
        verify(this.categoryRepo).delete((Category) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(Integer)}
     */
    @Test
    void testDeleteCategory3() {
        doNothing().when(this.categoryRepo).delete((Category) any());
        when(this.categoryRepo.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> this.categoryServiceImpl.deleteCategory(123));
        verify(this.categoryRepo).findById((Integer) any());
    }
}

