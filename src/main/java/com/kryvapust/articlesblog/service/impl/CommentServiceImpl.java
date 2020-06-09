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
    // ? или искать через Service
    UserRepository userRepository;
    ArticleRepository articleRepository;
    CommentMapper commentMapper;

    @Override
    public void add(CommentDto commentDto, Integer userId, Integer articleId) {
        // ? Не жирно ли два раза в БД ходить чтобы сущности достать и сохранить
        // проверить правильно ли я сохраняю модель - нужно ли заполнять
        User user=userRepository.findById(userId).orElse(null);
        Article article=articleRepository.findById(articleId).orElse(null);
        Comment comment = commentMapper.getComment(commentDto);
        comment.setArticle(article);
        comment.setUser(user);
        commentRepository.save(comment);
    }
}
