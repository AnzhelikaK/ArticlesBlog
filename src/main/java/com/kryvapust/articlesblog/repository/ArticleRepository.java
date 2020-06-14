package com.kryvapust.articlesblog.repository;

import com.kryvapust.articlesblog.model.Article;
import com.kryvapust.articlesblog.model.User;
import com.kryvapust.articlesblog.model.enums.ArticleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByUser(User user);

    @Modifying
    @Transactional
    @Query("update Article set status=:status where id=:articleId and user.id=:userId")
    Integer deleteById(@Param("articleId") Integer articleId, @Param("userId") Integer id, @Param("status") ArticleStatus status);
}
