package com.vtripathi.blogproject.Repository;

import com.vtripathi.blogproject.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
