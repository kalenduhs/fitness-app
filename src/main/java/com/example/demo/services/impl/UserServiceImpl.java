package com.example.demo.services.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.models.data.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User toEntity(UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole()
        );

        return user;
    }

    @Override
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );

        return userDto;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = toEntity(userDto);
        User savedUser = userRepository.save(user);
        return toDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers(String email) {
        List<User> users = new ArrayList<>();
        if (email != null && !email.isEmpty()) {
            userRepository.findByEmail(email).forEach(users::add);
        } else {
            userRepository.findAll().forEach(users::add);
        }
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }

}
