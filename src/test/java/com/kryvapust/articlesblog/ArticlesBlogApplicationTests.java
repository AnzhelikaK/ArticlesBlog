package com.kryvapust.articlesblog;

import com.kryvapust.articlesblog.model.enums.RoleName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArticlesBlogApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(cut(RoleName.ROLE_USER));
    }

    private String cut(RoleName roleName) {
        return roleName.toString().substring(5);
    }

}
