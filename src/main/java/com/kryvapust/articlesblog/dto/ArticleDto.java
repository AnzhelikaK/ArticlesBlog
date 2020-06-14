package com.kryvapust.articlesblog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kryvapust.articlesblog.model.enums.ArticleStatus;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDto {
    private Integer id;
    private String title;
    private String text;
    private ArticleStatus status;
    private String author;
    private Date created;
    private Date updated;
    private Set<String> tags;
}
