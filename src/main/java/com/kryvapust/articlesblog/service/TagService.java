package com.kryvapust.articlesblog.service;

import com.kryvapust.articlesblog.model.Tag;

import java.util.Set;

public interface TagService {
    Set<Tag> save(Set<String> tags);
}
