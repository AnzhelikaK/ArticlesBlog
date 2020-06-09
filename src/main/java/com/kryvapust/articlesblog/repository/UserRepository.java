package com.kryvapust.articlesblog.repository;

import com.kryvapust.articlesblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);


}
