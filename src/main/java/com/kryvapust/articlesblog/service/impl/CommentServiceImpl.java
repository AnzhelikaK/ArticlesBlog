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
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
    CommentMapper commentMapper;
    ArticleRepository articleRepository;

    @Override
    public void add(CommentDto commentDto, Integer userId, Integer articleId) {
        Comment comment = commentMapper.getComment(commentDto);
        comment.setArticleId(articleId);
        comment.setUserId(userId);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> getAllByArticle(Integer articleId) {
        List<Comment> byArticle = commentRepository.findAll(takeCommentExample(articleId));
        return byArticle.stream().map(commentMapper::getCommentDto).collect(Collectors.toList());
        // ? or return through variable
    }

    private Example<Comment> takeCommentExample(Integer articleId) {
        Comment comment = Comment.builder().setArticleId(articleId).build();
        return Example.of(comment);
    }

    @Override
    public CommentDto getOne(Integer commentId) {
        Comment comment=commentRepository.findById(commentId).orElse(new Comment());
        return commentMapper.getCommentDto(comment);
    }
}