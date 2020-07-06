package com.kryvapust.articlesblog.mapper;

import com.kryvapust.articlesblog.dto.UserDto;
import com.kryvapust.articlesblog.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User getUser(UserDto userDto) {
        return User.builder()
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setEmail(userDto.getEmail())
                .setPassword(userDto.getPassword())
                .build();
    }

    public UserDto getUserDto(User user) {
        return UserDto.builder()
                .setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setCreated(user.getCreated())
                .build();
    }
}
