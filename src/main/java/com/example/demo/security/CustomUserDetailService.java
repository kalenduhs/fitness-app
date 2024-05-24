package com.example.demo.security;

import com.example.demo.dto.UserDto;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDto> users = userService.getAllUsers(username);
        UserDto userDto = users.get(0);
        return new UserPrincipal(userDto.getId(), userDto.getEmail(), List.of(new SimpleGrantedAuthority(userDto.getRole())), userDto.getPassword());
    }
}
