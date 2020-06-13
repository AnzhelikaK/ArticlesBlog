package com.kryvapust.articlesblog.controller;

import com.kryvapust.articlesblog.dto.UserDto;
import com.kryvapust.articlesblog.mapper.UserMapper;
import com.kryvapust.articlesblog.model.User;
import com.kryvapust.articlesblog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor

@RestController
@RequestMapping(value = "/articlesBlog/account")
public class AccountController {
    private final UserService userService;

    @PostMapping(value = "/registration")
    public ResponseEntity createAccount(@Valid @RequestBody UserDto userDto) {
        userService.register(userDto);
        return ResponseEntity.ok("Success");
    }
}


