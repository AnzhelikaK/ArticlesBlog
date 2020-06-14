package com.kryvapust.articlesblog.service.impl;

import com.kryvapust.articlesblog.Exception.UserDontHaveRightsException;
import com.kryvapust.articlesblog.dto.ArticleDto;
import com.kryvapust.articlesblog.dto.PageDto;
import com.kryvapust.articlesblog.dto.SearchDto;
import com.kryvapust.articlesblog.mapper.ArticleMapper;
import com.kryvapust.articlesblog.model.Article;
import com.kryvapust.articlesblog.model.Tag;
import com.kryvapust.articlesblog.model.enums.ArticleStatus;
import com.kryvapust.articlesblog.model.User;
import com.kryvapust.articlesblog.repository.ArticleRepository;
import com.kryvapust.articlesblog.service.ArticleService;
import com.kryvapust.articlesblog.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final TagService tagService;
    private final EntityManager entityManager;

    private static final int SUCCESSFULLY_DELETED = 1;
    private static final int MAX_DATA_SIZE = 100;
    private static final String ASC_DIRECTION = "asc";
    private static final String SORT_BY_TITLE = "title";

    @Override
    public PageDto<ArticleDto> getAll(SearchDto searchDto) {
        int limit = MAX_DATA_SIZE;
        int offset = 0;

        if (searchDto.getLimit() != null && searchDto.getLimit() > 0) {
            if (searchDto.getLimit() < MAX_DATA_SIZE) {
                limit = searchDto.getLimit();
            }
        }
        if (searchDto.getSkip() != null && searchDto.getSkip() >= 0) {
            offset = searchDto.getSkip();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // creation CriteriaQuery for looking up
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        Root<Article> articleRoot = criteriaQuery.from(Article.class);
        Predicate criteria = criteriaBuilder.conjunction();

        // creation CriteriaQuery for counting size of selection
        CriteriaQuery<Long> criteriaQueryCount = criteriaBuilder.createQuery(Long.class);
        Root<Article> articleRootCount = criteriaQueryCount.from(Article.class);

        // set in parameters for looking up
        if (searchDto.getSearchKeyTitle() != null) {
            String searchKeyTitle = "%" + searchDto.getSearchKeyTitle() + "%";
            Predicate criteriaByTitle = criteriaBuilder.like(articleRoot.get("title"), searchKeyTitle);
            criteria = criteriaBuilder.and(criteria, criteriaByTitle);
        }

        if (searchDto.getSearchKeyAuthorId() != null) {
            User searchKeyAuthor = User.builder().setId(searchDto.getSearchKeyAuthorId()).build();
            Predicate criteriaByUser = criteriaBuilder.equal(articleRoot.get("user"), searchKeyAuthor);
            criteria = criteriaBuilder.and(criteria, criteriaByUser);
        }

        Predicate criteriaByStatus = criteriaBuilder.equal(articleRoot.get("status"), ArticleStatus.PUBLIC);
        criteria = criteriaBuilder.and(criteria, criteriaByStatus);

        // set in parameters for sorting and order
        String sortBy = SORT_BY_TITLE;
        if (searchDto.getSortBy() != null) {
            sortBy = searchDto.getSortBy();
        }

        String order = ASC_DIRECTION;
        if (searchDto.getOrder() != null) {
            order = searchDto.getOrder().toLowerCase();
        }
        if (ASC_DIRECTION.equals(order)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(articleRoot.get(sortBy)));
        } else criteriaQuery.orderBy(criteriaBuilder.desc(articleRoot.get(sortBy)));

        // execution query with settings for pagination
        criteriaQuery.select(articleRoot).where(criteria);
        List<Article> articles = entityManager
                .createQuery(criteriaQuery)
                .setMaxResults(limit)
                .setFirstResult(offset)
                .getResultList();

        // execution query for counting size of selection
        criteriaQueryCount.select(criteriaBuilder.count(articleRootCount)).where(criteria);
        Long totalNumber = entityManager.createQuery(criteriaQueryCount).getSingleResult();

        // form Page
        List<ArticleDto> articlesDto = articles.stream().map(articleMapper::getArticleDto).collect(Collectors.toList());
        return new PageDto<>(articlesDto, totalNumber, offset, limit);
    }

    @Override
    public List<ArticleDto> getAllByUser(Integer userId) {
        User user = User.builder().setId(userId).build();
        List<Article> articles = articleRepository.findByUser(user);
        return articles.stream().map(articleMapper::getArticleDto).collect(Collectors.toList());
    }

    @Override
    public void add(ArticleDto articleDto, Integer userId) {
        Article article = articleMapper.getArticle(articleDto);
        User user = User.builder().setId(userId).build();
        article.setUser(user);
        if (articleDto.getStatus() == null) {
            article.setStatus(ArticleStatus.DRAFT);
        }
        Set<Tag> articleTags = tagService.add(articleDto.getTags());
        article.setTags(articleTags);
        articleRepository.save(article);
    }

    @Override
    public void editArticle(Integer userId, Integer articleId, ArticleDto articleDto) throws UserDontHaveRightsException {
        if (userHaveRights(userId, articleId)) {
            User user = User.builder().setId(userId).build();
            Article updatedArticle = articleMapper.getArticle(articleDto);
            updatedArticle.setUser(user);
            updatedArticle.setId(articleId);
            Set<Tag> articleTags = tagService.add(articleDto.getTags());
            updatedArticle.setTags(articleTags);
            articleRepository.save(updatedArticle);
        } else {
            throw new UserDontHaveRightsException("You don't have rights to edit this article.");
        }
    }

    @Override
    public boolean delete(Integer articleId, Integer userId) {
        Integer result = articleRepository.deleteById(articleId, userId, ArticleStatus.DELETED);
        return (result == SUCCESSFULLY_DELETED);
    }

    private boolean userHaveRights(Integer userId, Integer articleId) {
        // Condition: Authenticated user ("userId") = Author of article ("articleId")
        Article article = articleRepository.findById(articleId).orElse(null);
        return article != null && article.getUser().getId().equals(userId);
    }
}
