package com.kryvapust.articlesblog.security.jwt;

import com.kryvapust.articlesblog.model.Role;
import com.kryvapust.articlesblog.model.UserStatus;
import com.kryvapust.articlesblog.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    //убрать попробовать
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                UserStatus.ACTIVE.equals(user.getStatus()),
                //user.getStatus().equals(UserStatus.ACTIVE),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }

}
