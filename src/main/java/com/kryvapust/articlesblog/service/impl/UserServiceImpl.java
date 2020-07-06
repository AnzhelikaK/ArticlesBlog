package com.kryvapust.articlesblog.service.impl;

import com.kryvapust.articlesblog.exception.UserRegistrationException;
import com.kryvapust.articlesblog.dto.UserDto;
import com.kryvapust.articlesblog.mapper.UserMapper;
import com.kryvapust.articlesblog.model.Role;
import com.kryvapust.articlesblog.model.enums.RoleName;
import com.kryvapust.articlesblog.model.enums.UserStatus;
import com.kryvapust.articlesblog.model.User;
import com.kryvapust.articlesblog.repository.RoleRepository;
import com.kryvapust.articlesblog.repository.UserRepository;
import com.kryvapust.articlesblog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public void register(UserDto userDto) throws UserRegistrationException {
        User existUser = userRepository.findByEmail(userDto.getEmail());
        if (existUser != null) {
            throw new UserRegistrationException("User already exist with this email.");
        }
        User user = userMapper.getUser(userDto);
        Role roleUser = roleRepository.findByName(RoleName.ROLE_USER);
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDto getById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        return userMapper.getUserDto(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id, UserStatus.DELETED);
    }
}
