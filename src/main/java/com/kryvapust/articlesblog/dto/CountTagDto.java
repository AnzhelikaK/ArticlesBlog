package com.kryvapust.articlesblog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder(setterPrefix = "set")
public class CountTagDto {
    private String tag;
    private Long ArticleCount;
}
