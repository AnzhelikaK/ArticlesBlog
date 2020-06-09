package com.kryvapust.articlesblog.repository;

import com.kryvapust.articlesblog.model.Article;
import com.kryvapust.articlesblog.model.User;
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
    @Query("update Article set status='DELETED' where id=:articleId and user.id=:userId")
    Integer deleteById(@Param("articleId") Integer articleId, @Param("userId") Integer userId);

//    @Modifying
//    @Transactional
//    @Query("update Article a set a.title=:article.title, text=:article.text, status=:article.status where id=:articleId")
//    Article update(@Param("articleId") Integer articleId, @Param("article") Article article);




}

