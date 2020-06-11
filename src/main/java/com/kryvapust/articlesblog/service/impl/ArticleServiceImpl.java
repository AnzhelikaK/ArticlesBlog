package com.kryvapust.articlesblog.service.impl;

import com.kryvapust.articlesblog.dto.ArticleDto;
import com.kryvapust.articlesblog.mapper.ArticleMapper;
import com.kryvapust.articlesblog.model.Article;
import com.kryvapust.articlesblog.model.Tag;
import com.kryvapust.articlesblog.model.enums.ArticleStatus;
import com.kryvapust.articlesblog.model.User;
import com.kryvapust.articlesblog.repository.ArticleRepository;
import com.kryvapust.articlesblog.repository.TagRepository;
import com.kryvapust.articlesblog.service.ArticleService;
import com.kryvapust.articlesblog.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

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
    private final TagRepository tagRepository;

    @Override
    public Article add(ArticleDto articleDto, Integer userId) {
        Article article = articleMapper.getArticle(articleDto);
        User user = userService.findById(userId);
        article.setUser(user);
        if (articleDto.getStatus() == null) {
            article.setStatus(ArticleStatus.DRAFT);
        }
        Set<Tag> tags = saveUniqueTagsAndReturnAll(articleDto.getTags());
        article.setTags(tags);
        Article createdArticle = articleRepository.save(article);
        return createdArticle;
    }

    private Set<Tag> saveUniqueTagsAndReturnAll(Set<String> tags) {
        HashSet<Tag> tagsFromDB = new HashSet<>();
        if (tags != null) {
            for (String t : tags) {
                Tag tag1 = tagRepository.findByName(t);
                if (tag1 != null) {
                    tagsFromDB.add(tag1);
                } else {

                    tagsFromDB.add(tagRepository.save(new Tag(t)));
                }
            }
        }

//        Iterator<String> tagsIter = tags.iterator();
//        while (tagsIter.hasNext()) {
//            String t = tagsIter.next();
//            Tag tag1 = tagRepository.findByName(t);
//            if (tag1 != null) {
//                tagsFromDB.add(tag1);
//            } else {
//                tagsFromDB.add(tagRepository.saveAndFlush(new Tag(t)));
//            }
//        }
        return tagsFromDB;
    }

    @Override
    public List<ArticleDto> getAll() {
        //     List<Article> article2s = articleRepository.findAll(takeArticleExample(ArticleStatus.PUBLIC));
        List<Article> byStatus = articleRepository.findByStatus(ArticleStatus.PUBLIC);
        return byStatus.stream().map(articleMapper::getArticleDto).collect(Collectors.toList());
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
