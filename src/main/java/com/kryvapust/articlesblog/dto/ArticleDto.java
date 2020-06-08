package com.kryvapust.articlesblog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kryvapust.articlesblog.model.Status;
import com.kryvapust.articlesblog.model.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Builder(setterPrefix = "set")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDto {
   private Integer id;
    private String title;
    private String text;
    private Status status;
    private String userName;
    private Date created;
    private Date updated;
}








