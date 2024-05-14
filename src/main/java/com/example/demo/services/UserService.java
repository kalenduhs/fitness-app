package com.example.demo.services;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

public interface UserService {

    User toEntity (UserDto userDto);
    UserDto toDto (User user);
    UserDto createUser(UserDto userDto);
}
