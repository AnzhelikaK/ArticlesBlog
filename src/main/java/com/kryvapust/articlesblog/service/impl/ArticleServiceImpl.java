package com.kryvapust.articlesblog.service.impl;

import com.kryvapust.articlesblog.dto.ArticleDto;
import com.kryvapust.articlesblog.mapper.ArticleMapper;
import com.kryvapust.articlesblog.model.Article;
import com.kryvapust.articlesblog.model.enums.ArticleStatus;
import com.kryvapust.articlesblog.model.User;
import com.kryvapust.articlesblog.repository.ArticleRepository;
import com.kryvapust.articlesblog.service.ArticleService;
import com.kryvapust.articlesblog.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final int SUCCESSFULLY_DELETED = 1;
    ArticleRepository articleRepository;
    UserService userService;
    ArticleMapper articleMapper;

    @Override
    public Article add(ArticleDto articleDto, Integer userId) {
        Article article = articleMapper.getArticle(articleDto);
        User user = userService.findById(userId);
        article.setUser(user);
        //?????? можно ли без проверки на null
        if(articleDto.getStatus()==null) {article.setStatus(ArticleStatus.DRAFT);}
        Article createdArticle = articleRepository.save(article);
        return createdArticle;
    }

    @Override
    public List<ArticleDto> getAll() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleDto> result = articles.stream().map(articleMapper::getArticleDto).collect(Collectors.toList());
        return result;
    }

//    @Override
//    public ArticleDto findById(Integer id) {
//        Article result = articleRepository.findById(id).orElse(null);
//
//        if (result == null) {
//            log.warn("IN findById - no Article found by id: {}", id);
//            return null;
//        }
//
//        log.info("IN findById - Article: {} found by id: {}", result, id);
//        return articleMapper.getArticleDto(result);
//
//    }

    @Override
    public List<ArticleDto> findAllByUser(String email) {
        User user = userService.findByEmail(email);
        List<Article> articles = articleRepository.findByUser(user);
        List<ArticleDto> result = articles.stream().map(articleMapper::getArticleDto).collect(Collectors.toList());
        return result;
    }

    @Override
    // нужно ли бросать ошибку, если пользователь не тот
    // как еще можно делать проверку на тот/не тот юзер
    public boolean editArticle(Integer userId, Integer articleId, ArticleDto articleDto) {
        if (checkArticleBelongUser(userId, articleId)) {
            User user = userService.findById(userId);
            Article updatedArticle = articleMapper.getArticle(articleDto);
            updatedArticle.setUser(user);
            updatedArticle.setId(articleId);
            //? Можно ли так присваивать значения переменным
            updatedArticle = articleRepository.save(updatedArticle);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer articleId, Integer userId) {
        Integer result = articleRepository.deleteById(articleId, userId);
        return (result == SUCCESSFULLY_DELETED);
    }

    private boolean checkArticleBelongUser(Integer userId, Integer articleId) {
        Article article = articleRepository.findById(articleId).orElse(null);
        return article != null && article.getUser().getId() == userId;
    }
}
