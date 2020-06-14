package com.kryvapust.articlesblog.controller;

import com.kryvapust.articlesblog.dto.UserDto;
import com.kryvapust.articlesblog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor

@RestController
@RequestMapping(value = "/articlesBlog/user")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Integer id) {
        UserDto response = userService.getById(id);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUser(@PathVariable(name = "id") Integer id) {
        userService.delete(id);
        return ResponseEntity.ok("User was successfully deleted.");
    }
}
