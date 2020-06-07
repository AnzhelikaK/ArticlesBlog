package com.kryvapust.articlesblog.mapper;

import com.kryvapust.articlesblog.dto.ArticleDto;
import com.kryvapust.articlesblog.model.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {
    public Article getArticle(ArticleDto articleDto) {
        Article article = new Article();
        article.setTitle(articleDto.getTitle());
        article.setText(articleDto.getText());
        return article;
    }

    public ArticleDto getArticleDto(Article article) {
        ArticleDto articleDto = ArticleDto.builder().setId().build();

        return articleDto;
    }
}
