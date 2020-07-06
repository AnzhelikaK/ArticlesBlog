package com.kryvapust.articlesblog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder(setterPrefix = "set")

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchDto {
    private Integer skip;
    private Integer limit;
    private String searchKeyTitle;
    private Integer searchKeyAuthorId;
    private String sortBy;
    private String order;
}
