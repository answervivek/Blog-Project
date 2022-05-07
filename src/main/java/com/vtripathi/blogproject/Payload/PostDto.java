package com.vtripathi.blogproject.Payload;

import com.vtripathi.blogproject.Entity.Category;
import com.vtripathi.blogproject.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addDate;

    private CategoryDto category;
    private UserDto user;
}

