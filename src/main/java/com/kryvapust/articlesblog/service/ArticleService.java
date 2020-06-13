package com.kryvapust.articlesblog.service;

import com.kryvapust.articlesblog.dto.ArticleDto;
import com.kryvapust.articlesblog.dto.PageDto;
import com.kryvapust.articlesblog.dto.SearchDto;
import com.kryvapust.articlesblog.model.Article;
import com.kryvapust.articlesblog.model.User;
import com.kryvapust.articlesblog.model.enums.ArticleStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    Article add(ArticleDto articleDto, Integer userId);

    PageDto<ArticleDto> getAll(SearchDto searchDto);

    List<ArticleDto> getAllByUser(String email);

 //  ArticleDto findById(Integer id);

    boolean delete(Integer articleId, Integer userId);

    boolean editArticle(Integer userId, Integer articleId, ArticleDto articleDto);
    //    Article edit(ArticleService articleService);

}
