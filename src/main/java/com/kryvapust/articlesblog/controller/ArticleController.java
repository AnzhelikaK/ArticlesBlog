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
        Integer userId = jwtTokenProvider.getUserId(request);
        Article createdArticle = articleService.add(articleDto, userId);
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

    @PutMapping(value = "/articles/{id}")
    public ResponseEntity editArticle(HttpServletRequest request, @RequestBody ArticleDto articleDto, @PathVariable(name = "id") Integer articleId) {
        Integer userId = jwtTokenProvider.getUserId(request);
        boolean result = articleService.editArticle(userId, articleId, articleDto);
        if (result) {
            return ResponseEntity.ok("Article " + articleDto.getTitle() + " was edited");
        }
        return ResponseEntity.ok("You can't edit this article");
    }

    @DeleteMapping(value = "/articles/{id}")
    public ResponseEntity deleteArticle(HttpServletRequest request, @PathVariable(name = "id") Integer articleId) {
        Integer userId = jwtTokenProvider.getUserId(request);
        boolean result = articleService.delete(articleId, userId);
        if (result) {
            return ResponseEntity.ok("Successfully deleted");
        }
        return ResponseEntity.ok("You can't delete this article");
    }

////???
//    @GetMapping(value = "/articles/{id}")
//    public ResponseEntity<ArticleDto> getArticleById(@PathVariable(name = "id") Integer id) {
//        ArticleDto result=articleService.findById(id);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

}



