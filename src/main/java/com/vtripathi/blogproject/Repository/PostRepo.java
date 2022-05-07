package com.vtripathi.blogproject.Repository;

import com.vtripathi.blogproject.Entity.Category;
import com.vtripathi.blogproject.Entity.Post;
import com.vtripathi.blogproject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
}
