package com.kryvapust.articlesblog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder(setterPrefix = "set")

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDto {
    private Integer id;
    private String message;
    private String articleTitle;
    private String author;
    private Date created;
}
