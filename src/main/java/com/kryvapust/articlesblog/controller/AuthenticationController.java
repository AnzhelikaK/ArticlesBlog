package com.kryvapust.articlesblog.controller;

import com.kryvapust.articlesblog.dto.AuthenticationDto;
import com.kryvapust.articlesblog.model.User;
import com.kryvapust.articlesblog.security.jwt.JwtTokenProvider;
import com.kryvapust.articlesblog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor

@RestController
@RequestMapping(value = "/articlesBlog")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AuthenticationDto requestDto) {
        try {
            String email = requestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));
            User user = userService.getByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("User with email: " + email + " not found");
            }
            String token = jwtTokenProvider.createToken(email, user.getRoles(), user.getId());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", email);
            response.put("token", token);
            response.put("id", user.getId());
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
