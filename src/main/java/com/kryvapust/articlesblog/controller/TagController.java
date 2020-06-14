package com.kryvapust.articlesblog.controller;

import com.kryvapust.articlesblog.dto.CountTagDto;
import com.kryvapust.articlesblog.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping(value = "/articlesBlog")
public class TagController {
    private final TagService tagService;

    @GetMapping("/tags-cloud")
    public ResponseEntity<List<CountTagDto>> getTagCloud() {
        List<CountTagDto> response = tagService.getTagWithNumberArticle();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
