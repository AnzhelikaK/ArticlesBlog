package com.kryvapust.articlesblog.service.impl;

import com.kryvapust.articlesblog.dto.UserDto;
import com.kryvapust.articlesblog.mapper.UserMapper;
import com.kryvapust.articlesblog.model.Role;
import com.kryvapust.articlesblog.model.enums.RoleName;
import com.kryvapust.articlesblog.model.enums.UserStatus;
import com.kryvapust.articlesblog.model.User;
import com.kryvapust.articlesblog.repository.RoleRepository;
import com.kryvapust.articlesblog.repository.UserRepository;
import com.kryvapust.articlesblog.service.UserService;
import com.sun.xml.fastinfoset.tools.FI_DOM_Or_XML_DOM_SAX_SAXEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@EnableAspectJAutoProxy
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Override
     public void register(UserDto userDto) {
        User user=userMapper.getUser(userDto);
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
        User result = userRepository.findByEmail(email);
       return result;
    }

    @Override
    public User getById(Integer id) {
        User result = userRepository.findById(id).orElse(null);
       return result;
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id, UserStatus.DELETED);
    }

}
