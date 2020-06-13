package com.kryvapust.articlesblog.service;

import com.kryvapust.articlesblog.dto.UserDto;
import com.kryvapust.articlesblog.model.User;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;
@EnableAspectJAutoProxy
public interface UserService {

    void register(UserDto userDto);

    User getByEmail(String email);

    User getById(Integer id);

    void delete(Integer id);
}
