package com.kryvapust.articlesblog.service;

import com.kryvapust.articlesblog.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User getByEmail(String email);

    User getById(Integer id);

    void delete(Integer id);
}
