package com.vtripathi.blogproject.ServiceImpl;

import com.vtripathi.blogproject.Entity.Category;
import com.vtripathi.blogproject.Exception.ResourceNotFoundException;
import com.vtripathi.blogproject.Payload.CategoryDto;
import com.vtripathi.blogproject.Repository.CategoryRepo;
import com.vtripathi.blogproject.Service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = dtoToCategory(categoryDto);
        Category saveCategory = this.categoryRepo.save(category);
        return this.categoryToDto(saveCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = this.categoryRepo.save(category);

        return this.categoryToDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
        return this.categoryToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> categoryDto = categories.stream().map(category -> this.categoryToDto(category)).collect(Collectors.toList());
        return categoryDto;
    }

    public Category dtoToCategory(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

    public CategoryDto categoryToDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
}
