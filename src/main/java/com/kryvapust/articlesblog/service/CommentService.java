package com.kryvapust.articlesblog.service;

import com.kryvapust.articlesblog.dto.CommentDto;
import com.kryvapust.articlesblog.model.Comment;

public interface CommentService {
    void add(CommentDto commentDto, Integer userId, Integer articleId);
}
