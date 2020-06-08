package com.kryvapust.articlesblog.mapper;

import com.kryvapust.articlesblog.dto.ArticleDto;
import com.kryvapust.articlesblog.model.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {
    public Article getArticle(ArticleDto articleDto) {
        return Article.builder()
                .setTitle(articleDto.getTitle())
                .setText(articleDto.getText())
                .build();
    }

    public ArticleDto getArticleDto(Article article) {
        return ArticleDto.builder()
                .setId(article.getId())
                .setTitle(article.getTitle())
                .setText(article.getText())
                .setStatus(article.getStatus())
                .setUserName(article.getUser().getFirstName())
                .setCreated(article.getCreated())
                .setUpdated(article.getUpdated())
                .build();
    }
}
