package com.example.usercrud.service;

import com.example.usercrud.dto.UserDto;
import com.example.usercrud.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUser(Long userId);
    List<User> getUsers();
    User registerUser(UserDto userDto);
    String deleteUser(Long userId);
    User updateUser(Long userId,UserDto userDto);
    User saveUser(User user, UserDto userDto);
}
