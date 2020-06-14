package com.kryvapust.articlesblog.repository;

import com.kryvapust.articlesblog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByName(String name);

    @Query("SELECT tag.name, COUNT(article) FROM Article article JOIN article.tags tag GROUP BY tag.name ORDER BY COUNT(article) DESC")
    List<Object[]> countByArticle();
}
