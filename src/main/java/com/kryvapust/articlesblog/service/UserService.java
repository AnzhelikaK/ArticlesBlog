package com.kryvapust.articlesblog.service;

import com.kryvapust.articlesblog.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();
// в примере было username
    User findByEmail(String username);

    User findById(Integer id);

    void delete(Integer id);
}
