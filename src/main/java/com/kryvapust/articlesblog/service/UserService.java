package com.kryvapust.articlesblog.service;

import com.kryvapust.articlesblog.dto.UserDto;
import com.kryvapust.articlesblog.model.User;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
public interface UserService {
    void register(UserDto userDto);

    User getByEmail(String email);

    UserDto getById(Integer id);

    void delete(Integer id);
}
