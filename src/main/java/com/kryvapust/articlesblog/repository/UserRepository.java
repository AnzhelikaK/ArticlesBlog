package com.kryvapust.articlesblog.repository;

import com.kryvapust.articlesblog.model.User;
import com.kryvapust.articlesblog.model.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);


    @Modifying
    @Transactional
    @Query("update User set status=:status where id=:id")
    void deleteById(@Param("id") Integer id, UserStatus status);


}
