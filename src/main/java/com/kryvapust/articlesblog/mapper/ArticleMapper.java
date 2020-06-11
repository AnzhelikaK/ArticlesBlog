package com.kryvapust.articlesblog.mapper;

import com.kryvapust.articlesblog.dto.ArticleDto;
import com.kryvapust.articlesblog.model.Article;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArticleMapper {
    private final TagMapper tagMapper;
    public Article getArticle(ArticleDto articleDto) {
        return Article.builder()
                .setTitle(articleDto.getTitle())
                .setText(articleDto.getText())
                .setStatus(articleDto.getStatus())
                .build();
    }
    public ArticleDto getArticleDto(Article article) {
        return ArticleDto.builder()
                .setId(article.getId())
                .setTitle(article.getTitle())
                .setText(article.getText())
                .setStatus(article.getStatus())
                .setAuthor(article.getUser().getFullName())
                .setCreated(article.getCreated())
                .setUpdated(article.getUpdated())
                .setTags(tagMapper.getTagDto(article.getTags()))
                .build();
    }
}
