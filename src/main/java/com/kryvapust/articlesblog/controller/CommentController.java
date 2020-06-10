package com.kryvapust.articlesblog.controller;


import com.kryvapust.articlesblog.dto.CommentDto;
import com.kryvapust.articlesblog.security.jwt.JwtTokenProvider;
import com.kryvapust.articlesblog.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping(value = "/articlesBlog/articles/{articleId}/comments")
public class CommentController {
    CommentService commentService;
    JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public ResponseEntity createComment(HttpServletRequest request,
                                        @PathVariable(name = "articleId") Integer articleId,
                                        @RequestBody CommentDto commentDto) {
        Integer userId = jwtTokenProvider.getUserId(request);
        commentService.add(commentDto, userId, articleId);
        return ResponseEntity.ok("Comment was added");
    }

    // +- ? Comments
    // доступен для всех пользователей(в задании не сказано четко)
    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllCommentsFromArticle(@PathVariable(name = "articleId") Integer articleId) {
        List<CommentDto> result = commentService.getAllByArticle(articleId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
