package com.kryvapust.articlesblog.service.impl;

import com.kryvapust.articlesblog.dto.ArticleDto;
import com.kryvapust.articlesblog.mapper.ArticleMapper;
import com.kryvapust.articlesblog.model.Article;
import com.kryvapust.articlesblog.model.Status;
import com.kryvapust.articlesblog.model.User;
import com.kryvapust.articlesblog.repository.ArticleRepository;
import com.kryvapust.articlesblog.service.ArticleService;
import com.kryvapust.articlesblog.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    ArticleRepository articleRepository;
    UserService userService;
    ArticleMapper articleMapper;

    @Override
    public Article add(ArticleDto articleDto, String email) {
        Article article = articleMapper.getArticle(articleDto);
        User user = userService.findByEmail(email);
        article.setUser(user);
        article.setStatus(Status.ACTIVE);
        Article createdArticle = articleRepository.save(article);
        return createdArticle;
    }

    @Override
    public List<ArticleDto> getAll() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleDto> result = articles.stream().map(articleMapper::getArticleDto).collect(Collectors.toList());
        return result;
    }

    @Override
    public ArticleDto findById(Integer id) {
        Article result = articleRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no Article found by id: {}", id);
            return null;
        }

        log.info("IN findById - Article: {} found by id: {}", result, id);
        return articleMapper.getArticleDto(result);

    }

    @Override
    public List<ArticleDto> findAllByUser(String email) {
        User user = userService.findByEmail(email);
        List<Article> articles = articleRepository.findByUser(user);
        List<ArticleDto> result = articles.stream().map(articleMapper::getArticleDto).collect(Collectors.toList());
        return result;
    }

    @Override
    public void delete(Integer id) {
        articleRepository.deleteById(id);
    }
}
