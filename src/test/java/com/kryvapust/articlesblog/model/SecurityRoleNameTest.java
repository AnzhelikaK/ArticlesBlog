package com.kryvapust.articlesblog.model;

import com.kryvapust.articlesblog.model.enums.RoleName;
import com.kryvapust.articlesblog.model.enums.SecurityRoleName;
import org.junit.Test;

public class SecurityRoleNameTest {
    @Test
    public void checkCompatibilityRoleName() {
        for (SecurityRoleName currentValue : SecurityRoleName.values()) {
            // test
            RoleName.valueOf("ROLE_" + currentValue.name());
        }
    }
}
