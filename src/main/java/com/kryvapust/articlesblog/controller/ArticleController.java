package com.kryvapust.articlesblog.controller;

import com.kryvapust.articlesblog.dto.ArticleDto;
import com.kryvapust.articlesblog.model.Article;
import com.kryvapust.articlesblog.security.jwt.JwtTokenProvider;
import com.kryvapust.articlesblog.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/articlesBlog")
public class ArticleController {

    JwtTokenProvider jwtTokenProvider;
    ArticleService articleService;

    @PostMapping(value = "/articles")
    public ResponseEntity createArticle(HttpServletRequest request, @RequestBody ArticleDto articleDto) {
        String email = jwtTokenProvider.getUsername(request);
        Article createdArticle = articleService.add(articleDto, email);
        return ResponseEntity.ok("Article " + createdArticle + " was created.");
    }

    @GetMapping(value = "/articles")
    public ResponseEntity<List<ArticleDto>> getAll() {
        List<ArticleDto> result = articleService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/articles/my")
    public ResponseEntity<List<ArticleDto>> getAllByUser(HttpServletRequest request) {
        String email = jwtTokenProvider.getUsername(request);
        List<ArticleDto> result = articleService.findAllByUser(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/articles/{id}")
    public ResponseEntity deleteArticle(HttpServletRequest request, @PathVariable(name = "id") Integer articleId) {
        Integer userId = jwtTokenProvider.getUserId(request);
        articleService.delete(articleId, userId);
        return ResponseEntity.ok("Successfully deleted");
    }
////???
//    @GetMapping(value = "/articles/{id}")
//    public ResponseEntity<ArticleDto> getArticleById(@PathVariable(name = "id") Integer id) {
//        ArticleDto result=articleService.findById(id);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

}



