package com.kryvapust.articlesblog.service;

import com.kryvapust.articlesblog.dto.ArticleDtoBuilder;
import com.kryvapust.articlesblog.model.Article;

public interface ArticleService {
    Article add(ArticleDtoBuilder articleDtoBuilder, Integer userId);
//    Article edit(ArticleService articleService);
//    List<Article> getAll();
//    List<Article> getAllByAuthor(User user);
//    void delete(Integer id);
}
