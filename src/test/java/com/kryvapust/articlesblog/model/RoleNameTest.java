package com.kryvapust.articlesblog.model;

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