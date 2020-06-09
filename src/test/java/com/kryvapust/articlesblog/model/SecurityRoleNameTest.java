package com.kryvapust.articlesblog.model;

import com.kryvapust.articlesblog.model.enums.RoleName;
import com.kryvapust.articlesblog.model.enums.SecurityRoleName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class SecurityRoleNameTest {
    @Test
    public void checkCompatibilityRoleName() {
        /*
        // prerequisites
        int a = 1;
        int b= 2;
        // expected   +-
        int expected = 3;
        // test
        Object actual = calculator.sum(a, b);
        // assert
        assertEquals(expected, actual);
        assertEquals(5, actual.hashCode());
        */

        for (SecurityRoleName currentValue : SecurityRoleName.values()) {
            // test
            RoleName.valueOf("ROLE_" + currentValue.name());
        }
    }
}