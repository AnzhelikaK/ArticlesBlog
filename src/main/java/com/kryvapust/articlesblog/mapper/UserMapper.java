package com.kryvapust.articlesblog.mapper;

import com.kryvapust.articlesblog.dto.UserDto;
import com.kryvapust.articlesblog.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User getUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setCreated_at(user.getCreated_at());
        return userDto;
    }
}
