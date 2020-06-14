package com.kryvapust.articlesblog.mapper;

import com.kryvapust.articlesblog.model.Tag;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TagMapper {
    public Set<String> getTagDto(Set<Tag> tags) {
        return tags.stream().map(Tag::toString).collect(Collectors.toSet());
    }
}
