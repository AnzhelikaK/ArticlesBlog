package com.kryvapust.articlesblog.controller;

import com.kryvapust.articlesblog.dto.ArticleDto;
import com.kryvapust.articlesblog.dto.PageDto;
import com.kryvapust.articlesblog.dto.SearchDto;
import com.kryvapust.articlesblog.security.jwt.JwtTokenProvider;
import com.kryvapust.articlesblog.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping(value = "/articlesBlog")
public class ArticleController {
    private final JwtTokenProvider jwtTokenProvider;
    private final ArticleService articleService;

    @GetMapping(value = "/articles")
    public ResponseEntity<PageDto<ArticleDto>> getAllArticle(@RequestParam(value = "skip", required = false) Integer skip,
                                                             @RequestParam(value = "limit", required = false) Integer limit,
                                                             @RequestParam(value = "q", required = false) String title,
                                                             @RequestParam(value = "author", required = false) Integer authorId,
                                                             @RequestParam(value = "sort", required = false) String sortBy,
                                                             @RequestParam(value = "order", required = false) String order) {
        SearchDto searchDto = SearchDto.builder()
                .setSkip(skip)
                .setLimit(limit)
                .setSearchKeyTitle(title)
                .setSearchKeyAuthorId(authorId)
                .setSortBy(sortBy)
                .setOrder(order)
                .build();
        PageDto<ArticleDto> response = articleService.getAll(searchDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/articles/my")
    public ResponseEntity<List<ArticleDto>> getArticleAllByUser(HttpServletRequest request) {
        Integer userId = jwtTokenProvider.getUserId(request);
        List<ArticleDto> response = articleService.getAllByUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/articles")
    public ResponseEntity createArticle(HttpServletRequest request,
                                        @RequestBody ArticleDto articleDto) {
        Integer userId = jwtTokenProvider.getUserId(request);
        articleService.add(articleDto, userId);
        return ResponseEntity.ok("Article was created.");
    }

    @PutMapping(value = "/articles/{id}")
    public ResponseEntity editArticle(HttpServletRequest request,
                                      @RequestBody ArticleDto articleDto,
                                      @PathVariable(name = "id") Integer articleId) {
        Integer userId = jwtTokenProvider.getUserId(request);
        articleService.editArticle(userId, articleId, articleDto);
        return ResponseEntity.ok("Article was edited.");
    }

    @DeleteMapping(value = "/articles/{id}")
    public ResponseEntity deleteArticle(HttpServletRequest request, @PathVariable(name = "id") Integer articleId) {
        Integer userId = jwtTokenProvider.getUserId(request);
        boolean result = articleService.delete(articleId, userId);
        if (result) {
            return ResponseEntity.ok("Article was successfully deleted.");
        }
        return ResponseEntity.ok("You can't delete this article.");
    }
}
