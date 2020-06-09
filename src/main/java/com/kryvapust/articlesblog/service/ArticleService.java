package com.kryvapust.articlesblog.service;

import com.kryvapust.articlesblog.dto.ArticleDto;
import com.kryvapust.articlesblog.model.Article;
import com.kryvapust.articlesblog.model.User;

import java.util.List;

public interface ArticleService {
    Article add(ArticleDto articleDto, String email);

    List<ArticleDto> getAll();

    List<ArticleDto> findAllByUser(String email);

    ArticleDto findById(Integer id);

    void delete(Integer articleId, Integer userId);
    //    Article edit(ArticleService articleService);

}
