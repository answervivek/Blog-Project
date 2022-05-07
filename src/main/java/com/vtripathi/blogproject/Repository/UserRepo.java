package com.vtripathi.blogproject.Repository;

import com.vtripathi.blogproject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

}
