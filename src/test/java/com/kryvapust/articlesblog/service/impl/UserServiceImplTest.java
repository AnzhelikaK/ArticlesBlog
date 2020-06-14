package com.kryvapust.articlesblog.service.impl;

import com.kryvapust.articlesblog.dto.UserDto;
import com.kryvapust.articlesblog.mapper.UserMapper;
import com.kryvapust.articlesblog.model.User;
import com.kryvapust.articlesblog.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImp;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @Test
    public void getByEmail() {
        // prerequisites
        String email = "123@gmail.com";
        User user = new User();
        Mockito.when(userRepository.findByEmail(email)).thenReturn(user);

        // test
        User actual = userServiceImp.getByEmail(email);
        
        // assert
        assertEquals(user, actual);
    }

    @Test
    public void getById() {
        // prerequisites
        Integer id = 4;
        UserDto userDto = new UserDto();
        User user = new User();
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        Mockito.when(userMapper.getUserDto(user)).thenReturn(userDto);

        // test
        UserDto actual = userServiceImp.getById(id);

        // assert
        assertEquals(userDto, actual);
    }
}
