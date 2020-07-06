package com.kryvapust.articlesblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageDto<T> {
    private List<T> data;
    private long totalNumber;
    private int skip;
    private int limit;
}
