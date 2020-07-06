package com.kryvapust.articlesblog.service.impl;

import com.kryvapust.articlesblog.dto.CountTagDto;
import com.kryvapust.articlesblog.model.Tag;
import com.kryvapust.articlesblog.repository.TagRepository;
import com.kryvapust.articlesblog.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    public Set<Tag> add(Set<String> inPutTags) {
        Set<Tag> outPutTag = new HashSet<>();
        if (inPutTags != null) {
            for (String tag : inPutTags) {
                Tag existedTag = tagRepository.findByName(tag);
                if ((existedTag != null)) {
                    outPutTag.add(existedTag);
                } else {
                    outPutTag.add(tagRepository.save(new Tag(tag)));
                }
            }
        }
        return outPutTag;
    }

    public List<CountTagDto> getTagWithNumberArticle() {
        List<Object[]> countTags = tagRepository.countByArticle();
        List<CountTagDto> result = new ArrayList<>();
        countTags.forEach(obj -> {
            result.add(CountTagDto.builder()
                    .setTag((String) obj[0])
                    .setArticleCount((Long) obj[1])
                    .build());
        });
        return result;
    }
}
