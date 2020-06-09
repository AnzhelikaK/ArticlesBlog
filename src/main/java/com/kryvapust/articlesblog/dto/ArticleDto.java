package com.kryvapust.articlesblog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.kryvapust.articlesblog.model.enums.ArticleStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder(setterPrefix = "set")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDto {
   private Integer id;
    private String title;
    private String text;
    private ArticleStatus status;
    private String author;
    private Date created;
    private Date updated;
}








