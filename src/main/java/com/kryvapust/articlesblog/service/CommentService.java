package com.kryvapust.articlesblog.service;

import com.kryvapust.articlesblog.dto.CommentDto;
import com.kryvapust.articlesblog.model.Comment;

import java.util.List;

public interface CommentService {
    void add(CommentDto commentDto, Integer userId, Integer articleId);
    List<CommentDto> getAllByArticle(Integer articleId);
}
