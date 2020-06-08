package com.kryvapust.articlesblog.controller;

import com.kryvapust.articlesblog.dto.UserDto;
import com.kryvapust.articlesblog.mapper.UserMapper;
import com.kryvapust.articlesblog.model.User;
import com.kryvapust.articlesblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/articlesBlog/account") // account
public class AccountController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public AccountController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(value = "/registration")
    public ResponseEntity createAccount(@RequestBody UserDto requestUserDto) {
        User result = userMapper.getUser(requestUserDto);
        userService.register(result);
        return ResponseEntity.ok("Success");
    }
}


