package com.kryvapust.articlesblog.service;

import com.kryvapust.articlesblog.dto.UserDto;
import com.kryvapust.articlesblog.model.User;

public interface UserService {
    void register(UserDto userDto);

    User getByEmail(String email);

    UserDto getById(Integer id);

    void delete(Integer id);
}
