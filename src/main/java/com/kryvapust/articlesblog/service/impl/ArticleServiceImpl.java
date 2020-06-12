package com.kryvapust.articlesblog.service.impl;

import com.kryvapust.articlesblog.dto.ArticleDto;
import com.kryvapust.articlesblog.dto.SearchDto;
import com.kryvapust.articlesblog.mapper.ArticleMapper;
import com.kryvapust.articlesblog.model.Article;
import com.kryvapust.articlesblog.model.Tag;
import com.kryvapust.articlesblog.model.enums.ArticleStatus;
import com.kryvapust.articlesblog.model.User;
import com.kryvapust.articlesblog.repository.ArticleRepository;
import com.kryvapust.articlesblog.service.ArticleService;
import com.kryvapust.articlesblog.service.TagService;
import com.kryvapust.articlesblog.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private static final int SUCCESSFULLY_DELETED = 1;
    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final ArticleMapper articleMapper;
    private final TagService tagService;
    private final EntityManager entityManager;

    @Override
    public Article add(ArticleDto articleDto, Integer userId) {
        Article article = articleMapper.getArticle(articleDto);
        User user = userService.getById(userId);
        article.setUser(user);
        if (articleDto.getStatus() == null) {
            article.setStatus(ArticleStatus.DRAFT);
        }
        Set<Tag> articleTags = tagService.save(articleDto.getTags());
        article.setTags(articleTags);
        Article createdArticle = articleRepository.save(article);
        return createdArticle;
    }

    @Override
    public List<ArticleDto> getAll(SearchDto searchDto) {
        int limit = 100;
        int offset = 0;
        if (searchDto.getLimit() != null) {
            if (searchDto.getLimit() < 100 && searchDto.getLimit() > 0) {
                limit = searchDto.getLimit();
            }
        }
        if (searchDto.getSkip() != null) {
            if (searchDto.getSkip() >= 0) {
                offset = searchDto.getSkip() * limit;
            }
        }
        String ASC_DIRECTION = "asc";

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        Root<Article> articleRoot = criteriaQuery.from(Article.class);
        Predicate criteria = criteriaBuilder.conjunction();

        if (searchDto.getPostTitle() != null) {
            Predicate title = criteriaBuilder.equal(articleRoot.get("title"), searchDto.getPostTitle());
            criteria = criteriaBuilder.and(criteria, title);
        }
        if (searchDto.getAuthorId() != null) {
            Predicate user = criteriaBuilder.equal(articleRoot.get("user"), User.builder().setId(searchDto.getAuthorId()).build());
            criteria = criteriaBuilder.and(criteria, user);
        }

        Predicate status = criteriaBuilder.equal(articleRoot.get("status"), ArticleStatus.PUBLIC);
        criteria = criteriaBuilder.and(criteria, status);


        String sortBy = "title";
        if (searchDto.getSort() != null) {
            sortBy = searchDto.getSort();
        }
        String order = "asc";
        if (searchDto.getOrder() != null) {
            order = searchDto.getOrder().toLowerCase();
        }
        if (ASC_DIRECTION.equals(order)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(articleRoot.get(sortBy)));
        } else criteriaQuery.orderBy(criteriaBuilder.desc(articleRoot.get(sortBy)));

        criteriaQuery.select(articleRoot).where(criteria);
        List<Article> articles = entityManager
                .createQuery(criteriaQuery)
                .setMaxResults(limit)
                .setFirstResult(offset)
                .getResultList();
        return articles.stream().map(articleMapper::getArticleDto).collect(Collectors.toList());

//        List<Article> byStatus = articleRepository.findByStatus(ArticleStatus.PUBLIC, page);
//        return byStatus.stream().map(articleMapper::getArticleDto).collect(Collectors.toList());

    }

    @Override
    public List<ArticleDto> getAllByUser(String email) {
        User user = userService.getByEmail(email);
        List<Article> articles = articleRepository.findByUser(user);
        List<ArticleDto> result = articles.stream().map(articleMapper::getArticleDto).collect(Collectors.toList());
        return result;
    }

    @Override
    // нужно ли бросать ошибку, если пользователь не тот
    // как еще можно делать проверку на тот/не тот юзер
    public boolean editArticle(Integer userId, Integer articleId, ArticleDto articleDto) {
        if (checkArticleBelongUser(userId, articleId)) {
            User user = userService.getById(userId);
            Article updatedArticle = articleMapper.getArticle(articleDto);
            updatedArticle.setUser(user);
            updatedArticle.setId(articleId);
            //? Можно ли так присваивать значения переменным/
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
