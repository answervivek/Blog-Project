package com.vtripathi.blogproject.Service;

import com.vtripathi.blogproject.Payload.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);
}

