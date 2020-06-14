package com.kryvapust.articlesblog.service.impl;

import com.kryvapust.articlesblog.Exception.UserDontHaveRightsException;
import com.kryvapust.articlesblog.dto.CommentDto;
import com.kryvapust.articlesblog.dto.SearchDto;
import com.kryvapust.articlesblog.mapper.CommentMapper;
import com.kryvapust.articlesblog.model.Comment;
import com.kryvapust.articlesblog.repository.CommentRepository;
import com.kryvapust.articlesblog.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    private static final String DESC_DIRECTION = "desc";
    private static final String SORT_BY_DATE = "created";

    @Override
    public List<CommentDto> getAllByArticle(Integer articleId, SearchDto searchDto) {
        String sortBy = SORT_BY_DATE;
        if (searchDto.getSortBy() != null) {
            sortBy = searchDto.getSortBy();
        }
        Sort sort = new Sort(Sort.Direction.ASC, sortBy);
        if (searchDto.getOrder() != null && DESC_DIRECTION.equals(searchDto.getOrder().toLowerCase())) {
            sort = new Sort(Sort.Direction.DESC, sortBy);
        }
        List<Comment> byArticle = commentRepository.findAll(takeCommentExample(articleId, searchDto), sort);
        return byArticle.stream().map(commentMapper::getCommentDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getOne(Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(new Comment());
        return commentMapper.getCommentDto(comment);
    }

    @Override
    public void add(CommentDto commentDto, Integer userId, Integer articleId) {
        Comment comment = commentMapper.getComment(commentDto);
        comment.setArticleId(articleId);
        comment.setUserId(userId);
        commentRepository.save(comment);
    }

    @Override
    public void delete(Integer commentId, Integer userId) throws UserDontHaveRightsException {
        Comment comment = commentRepository.findById(commentId).orElse(new Comment());
        if (userHaveRights(comment, userId)) {
            commentRepository.delete(comment);
        } else throw new UserDontHaveRightsException("You don't have rights to delete this comment");
    }

    private boolean userHaveRights(Comment comment, Integer userId) {
        // Condition: Authenticated user ("userId") = Author of comment OR Author of article
        return userId.equals(comment.getUserId()) || userId.equals(comment.getArticle().getUser().getId());
    }

    private Example<Comment> takeCommentExample(Integer articleId, SearchDto searchDto) {
        Comment comment = Comment.builder()
                .setUserId(searchDto.getSearchKeyAuthorId())
                .setArticleId(articleId)
                .build();
        return Example.of(comment);
    }
}
