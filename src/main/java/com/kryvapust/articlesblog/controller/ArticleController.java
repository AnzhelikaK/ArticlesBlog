package com.kryvapust.articlesblog.controller;

import com.kryvapust.articlesblog.dto.ArticleDto;
import com.kryvapust.articlesblog.model.Article;
import com.kryvapust.articlesblog.security.jwt.JwtTokenProvider;
import com.kryvapust.articlesblog.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/articlesBlog")
public class ArticleController {

    JwtTokenProvider jwtTokenProvider;
    ArticleService articleService;

    @PostMapping(value = "/articles")
    public ResponseEntity createArticle(HttpServletRequest request, @RequestBody ArticleDto articleDto) {
        String id = jwtTokenProvider.getUserId(request);
        String token = jwtTokenProvider.resolveToken(request);
        String name = jwtTokenProvider.getUsername(token);

        Integer userId = Integer.valueOf(id);
        Article result = articleService.add(articleDto, userId);
        return ResponseEntity.ok("Article " + result + " was created.");
    }
}



