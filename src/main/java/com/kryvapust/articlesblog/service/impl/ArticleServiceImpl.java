package com.kryvapust.articlesblog.service.impl;

import com.kryvapust.articlesblog.dto.ArticleDtoBuilder;
import com.kryvapust.articlesblog.mapper.ArticleMapper;
import com.kryvapust.articlesblog.model.Article;
import com.kryvapust.articlesblog.model.User;
import com.kryvapust.articlesblog.repository.ArticleRepository;
import com.kryvapust.articlesblog.service.ArticleService;
import com.kryvapust.articlesblog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    ArticleRepository articleRepository;
    UserService userService; // ?????
    ArticleMapper articleMapper;

    @Override
    public Article add(ArticleDtoBuilder articleDtoBuilder, Integer userId) {
        Article article = articleMapper.getArticle(articleDtoBuilder);
        User user = userService.findById(userId);
        article.setUser(user);
        Article createdArticle = articleRepository.save(article);

        return createdArticle;
    }
}
