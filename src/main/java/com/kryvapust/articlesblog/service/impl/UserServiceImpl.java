package com.kryvapust.articlesblog.service.impl;

import com.kryvapust.articlesblog.model.Role;
import com.kryvapust.articlesblog.model.RoleName;
import com.kryvapust.articlesblog.model.Status;
import com.kryvapust.articlesblog.model.User;
import com.kryvapust.articlesblog.repository.RoleRepository;
import com.kryvapust.articlesblog.repository.UserRepository;
import com.kryvapust.articlesblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {

        Role roleUser = roleRepository.findByName(RoleName.ROLE_USER);
//        Role role=new Role();
//        role.setName(RoleName.TEST);
//        roleRepository.save(role);
//        roleRepository.exists(role);
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByEmail(String username) {
        User result = userRepository.findByEmail(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Integer id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }

}
