package com.kryvapust.articlesblog.service;

import com.kryvapust.articlesblog.dto.ArticleDto;
import com.kryvapust.articlesblog.dto.PageDto;
import com.kryvapust.articlesblog.dto.SearchDto;

import java.util.List;

public interface ArticleService {
    PageDto<ArticleDto> getAll(SearchDto searchDto);

    List<ArticleDto> getAllByUser(Integer userId);

    void add(ArticleDto articleDto, Integer userId);

    void editArticle(Integer userId, Integer articleId, ArticleDto articleDto) throws RuntimeException;

    boolean delete(Integer articleId, Integer userId);
}
