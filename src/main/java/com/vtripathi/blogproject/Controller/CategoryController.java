package com.vtripathi.blogproject.Controller;

import com.vtripathi.blogproject.Payload.ApiResponse;
import com.vtripathi.blogproject.Payload.CategoryDto;
import com.vtripathi.blogproject.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
        CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
      this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully", true ),HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
        CategoryDto categoryDto = this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<CategoryDto>> getAllCategory(){
        Iterable<CategoryDto> categoryDto = this.categoryService.getAllCategories();
        return new ResponseEntity<Iterable<CategoryDto>>(categoryDto, HttpStatus.OK);
    }
}

