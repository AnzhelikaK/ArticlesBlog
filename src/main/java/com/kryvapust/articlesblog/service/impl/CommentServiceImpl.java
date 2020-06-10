package com.kryvapust.articlesblog.service.impl;

import com.kryvapust.articlesblog.dto.CommentDto;
import com.kryvapust.articlesblog.mapper.CommentMapper;
import com.kryvapust.articlesblog.model.Article;
import com.kryvapust.articlesblog.model.Comment;
import com.kryvapust.articlesblog.model.User;
import com.kryvapust.articlesblog.repository.ArticleRepository;
import com.kryvapust.articlesblog.repository.CommentRepository;
import com.kryvapust.articlesblog.repository.UserRepository;
import com.kryvapust.articlesblog.service.ArticleService;
import com.kryvapust.articlesblog.service.CommentService;
import com.kryvapust.articlesblog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
    CommentMapper commentMapper;

    @Override
    public void add(CommentDto commentDto, Integer userId, Integer articleId) {
        Comment comment = commentMapper.getComment(commentDto);
        comment.setArticleId(articleId);
        comment.setUserId(userId);
        commentRepository.save(comment);
    }
}
