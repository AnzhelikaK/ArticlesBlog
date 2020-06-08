package com.kryvapust.articlesblog.repository;

import com.kryvapust.articlesblog.model.Article;
import com.kryvapust.articlesblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByUser(User user);

    @Override
    @Modifying
    @Query("update Article set status='DELETED' where id=:id")
    void deleteById(@Param("id") Integer id);
}

//update articles set status='DELETED' where id=19;
