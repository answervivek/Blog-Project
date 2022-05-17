package com.vtripathi.blogproject.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtripathi.blogproject.Payload.CategoryDto;
import com.vtripathi.blogproject.Service.CategoryService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CategoryController.class})
@ExtendWith(SpringExtension.class)
class CategoryControllerTest {
    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryService categoryService;

    /**
     * Method under test: {@link CategoryController#createCategory(CategoryDto)}
     */
    @Test
    void testCreateCategory() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(categoryDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/category/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CategoryController#createCategory(CategoryDto)}
     */
    @Test
    void testCreateCategory2() throws Exception {
        when(this.categoryService.createCategory((CategoryDto) any())).thenReturn(new CategoryDto());

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Category Title");
        String content = (new ObjectMapper()).writeValueAsString(categoryDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/category/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"categoryId\":null,\"categoryTitle\":null,\"categoryDescription\":null}"));
    }

    /**
     * Method under test: {@link CategoryController#updateCategory(CategoryDto, Integer)}
     */
    @Test
    void testUpdateCategory2() throws Exception {
        when(this.categoryService.updateCategory((CategoryDto) any(), (Integer) any())).thenReturn(new CategoryDto());

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Category Title");
        String content = (new ObjectMapper()).writeValueAsString(categoryDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/category/{categoryId}", 123)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"categoryId\":null,\"categoryTitle\":null,\"categoryDescription\":null}"));
    }

    /**
     * Method under test: {@link CategoryController#deleteCategory(Integer)}
     */
    @Test
    void testDeleteCategory() throws Exception {
        doNothing().when(this.categoryService).deleteCategory((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/category/{categoryId}", 123);
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"message\":\"Category Deleted Successfully\",\"success\":true}"));
    }

    /**
     * Method under test: {@link CategoryController#deleteCategory(Integer)}
     */
    @Test
    void testDeleteCategory2() throws Exception {
        doNothing().when(this.categoryService).deleteCategory((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/category/{categoryId}", 123);
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"message\":\"Category Deleted Successfully\",\"success\":true}"));
    }

    /**
     * Method under test: {@link CategoryController#getAllCategory()}
     */
    @Test
    void testGetAllCategory() throws Exception {
        when(this.categoryService.getAllCategories()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/category/");
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CategoryController#getAllCategory()}
     */
    @Test
    void testGetAllCategory2() throws Exception {
        when(this.categoryService.getAllCategories()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/category/");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CategoryController#getCategory(Integer)}
     */
    @Test
    void testGetCategory() throws Exception {
        when(this.categoryService.getCategoryById((Integer) any())).thenReturn(new CategoryDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/category/{categoryId}", 123);
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"categoryId\":null,\"categoryTitle\":null,\"categoryDescription\":null}"));
    }

    /**
     * Method under test: {@link CategoryController#getCategory(Integer)}
     */
    @Test
    void testGetCategory2() throws Exception {
        when(this.categoryService.getCategoryById((Integer) any())).thenReturn(new CategoryDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/category/{categoryId}", 123);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"categoryId\":null,\"categoryTitle\":null,\"categoryDescription\":null}"));
    }

    /**
     * Method under test: {@link CategoryController#getCategory(Integer)}
     */
    @Test
    void testGetCategory3() throws Exception {
        when(this.categoryService.getAllCategories()).thenReturn(new ArrayList<>());
        when(this.categoryService.getCategoryById((Integer) any())).thenReturn(new CategoryDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/category/{categoryId}", "",
                "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CategoryController#getCategory(Integer)}
     */
    @Test
    void testGetCategory4() throws Exception {
        when(this.categoryService.getAllCategories()).thenReturn(new ArrayList<>());
        when(this.categoryService.getCategoryById((Integer) any())).thenReturn(new CategoryDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/category/{categoryId}", "", "Uri Vars");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CategoryController#updateCategory(CategoryDto, Integer)}
     */
    @Test
    void testUpdateCategory() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(categoryDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/category/{categoryId}", 123)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

