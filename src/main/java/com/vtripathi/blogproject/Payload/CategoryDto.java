package com.vtripathi.blogproject.Payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer categoryId;

    @NotEmpty
    @Size(min=3, max=15, message = "Category Title must be between 3 and 15 characters")
    private String categoryTitle;

    @NotEmpty
    @Size(min=5, message = "Category Description must more than 5 characters")
    private String categoryDescription;
}
