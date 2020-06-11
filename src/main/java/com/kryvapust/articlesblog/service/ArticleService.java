package com.kryvapust.articlesblog.service;

import com.kryvapust.articlesblog.dto.ArticleDto;
import com.kryvapust.articlesblog.model.Article;
import com.kryvapust.articlesblog.model.User;

import java.util.List;

public interface ArticleService {
    Article add(ArticleDto articleDto, Integer userId);

    List<ArticleDto> getAll();

    List<ArticleDto> getAllByUser(String email);

 //  ArticleDto findById(Integer id);

    boolean delete(Integer articleId, Integer userId);

    boolean editArticle(Integer userId, Integer articleId, ArticleDto articleDto);
    //    Article edit(ArticleService articleService);

}
