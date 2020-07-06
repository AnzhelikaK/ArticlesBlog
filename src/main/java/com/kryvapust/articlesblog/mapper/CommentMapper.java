package com.kryvapust.articlesblog.mapper;

import com.kryvapust.articlesblog.dto.CommentDto;
import com.kryvapust.articlesblog.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment getComment(CommentDto commentDto) {
        return Comment.builder()
                .setMessage(commentDto.getMessage())
                .build();
    }

    public CommentDto getCommentDto(Comment comment) {
        return CommentDto.builder()
                .setId(comment.getId())
                .setMessage(comment.getMessage())
                .setArticleTitle(comment.getArticle().getTitle())
                .setAuthor(comment.getUser().getFullName())
                .setCreated(comment.getCreated())
                .build();
    }
}
