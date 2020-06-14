package com.kryvapust.articlesblog.aspect;

import com.kryvapust.articlesblog.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j

@Aspect
@Component
public class UserAspect {
    @After("execution(public void com.kryvapust.articlesblog.service.impl.UserServiceImpl.register(..))&& args(user)")
    public void register(UserDto user) {
        log.info("New {} was successfully registered.", user);
    }

    @After("execution(public * com.kryvapust.articlesblog.service.impl.UserServiceImpl.getById(..))")
    public void getById(JoinPoint joinPoint) {
        log.info("Method {} was called.", joinPoint.toString());
    }

    @After("execution(public * com.kryvapust.articlesblog.service.impl.UserServiceImpl.getByEmail(..))")
    public void getByEmail(JoinPoint joinPoint) {
        log.info("Method {} was called.", joinPoint.toString());
    }

    @After("execution(public void com.kryvapust.articlesblog.service.impl.UserServiceImpl.delete(..))&& args(id)")
    public void delete(Integer id) {
        log.info("User with id: {} was \"softy\" deleted from DB.", id);
    }
}
