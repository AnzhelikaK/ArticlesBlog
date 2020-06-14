package com.kryvapust.articlesblog.service;

import com.kryvapust.articlesblog.dto.CountTagDto;
import com.kryvapust.articlesblog.model.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {
    Set<Tag> add(Set<String> tags);

    List<CountTagDto> getTagWithNumberArticle();
}
