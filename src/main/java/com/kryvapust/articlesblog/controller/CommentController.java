package com.kryvapust.articlesblog.controller;

import com.kryvapust.articlesblog.dto.CommentDto;
import com.kryvapust.articlesblog.dto.SearchDto;
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

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable(name = "articleId") Integer articleId,
                                                           @RequestParam(value = "skip", required = false) Integer skip,
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
        List<CommentDto> response = commentService.getAllByArticle(articleId, searchDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{commentId}")
    public ResponseEntity<CommentDto> getOneComment(@PathVariable Integer commentId) {
        CommentDto response = commentService.getOne(commentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createComment(HttpServletRequest request,
                                        @PathVariable(name = "articleId") Integer articleId,
                                        @RequestBody CommentDto commentDto) {
        Integer userId = jwtTokenProvider.getUserId(request);
        commentService.add(commentDto, userId, articleId);
        return ResponseEntity.ok("Comment was added");
    }

    @DeleteMapping(value = "/{commentId}")
    public ResponseEntity deleteComment(HttpServletRequest request,
                                        @PathVariable Integer commentId) {
        Integer userId = jwtTokenProvider.getUserId(request);
        commentService.delete(commentId, userId);
        return ResponseEntity.ok("Comment was deleted.");
    }
}
