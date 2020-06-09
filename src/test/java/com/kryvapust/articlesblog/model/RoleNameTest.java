package com.kryvapust.articlesblog.model;

import com.kryvapust.articlesblog.model.enums.RoleName;
import com.kryvapust.articlesblog.model.enums.SecurityRoleName;
import org.junit.Test;

public class RoleNameTest {
    @Test
    public void checkCompatibilitySecurityRoleName() {
        for (RoleName currentValue : RoleName.values()) {
            //test
            SecurityRoleName.valueOf(currentValue.name().replace("ROLE_", ""));
        }
    }
}