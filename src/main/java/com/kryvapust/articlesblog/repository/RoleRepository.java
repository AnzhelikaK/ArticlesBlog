package com.kryvapust.articlesblog.repository;

import com.kryvapust.articlesblog.model.Role;
import com.kryvapust.articlesblog.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(RoleName name);
}
