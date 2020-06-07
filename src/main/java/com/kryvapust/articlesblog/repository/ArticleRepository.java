package com.kryvapust.articlesblog.repository;

import com.kryvapust.articlesblog.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
