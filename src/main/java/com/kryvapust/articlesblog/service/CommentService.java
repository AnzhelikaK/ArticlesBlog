package com.kryvapust.articlesblog.service;

import com.kryvapust.articlesblog.dto.CommentDto;
import com.kryvapust.articlesblog.dto.SearchDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAllByArticle(Integer articleId, SearchDto searchDto);

    CommentDto getOne(Integer commentId);

    void add(CommentDto commentDto, Integer userId, Integer articleId);

    void delete(Integer commentId, Integer userId) throws RuntimeException;
}
