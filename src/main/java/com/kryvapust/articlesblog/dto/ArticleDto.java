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
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDto {
    public ArticleDto(Integer id, String title, String text, Status status, User user, Date created_at, Date updated_at) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.status = status;
        this.user = user;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    private Integer id;
    private String title;
    private String text;
    private Status status;
    private User user;
    private Date created_at;
    private Date updated_at;

    public static ArticleDtoBuilder builder(){
        return new ArticleDtoBuilder();
}
  public static class ArticleDtoBuilder {
        private  Integer id;
        private   String title;
        private  String text;
        private  Status status;
        private  User user;
        private  Date created_at;
        private  Date updated_at;

        public ArticleDtoBuilder setId(Integer id) {
            this.id = id;
            return this;
        }
        public ArticleDtoBuilder setTitle(String title) {
            this.title = title;
            return this;
        }
        public ArticleDtoBuilder setText(String text) {
            this.text = text;
            return this;
        }
        public ArticleDtoBuilder setStatus(Status status) {
            this.status = status;
            return this;
        }
        public ArticleDtoBuilder setUser(User user) {
            this.user = user;
            return this;
        }
        public ArticleDtoBuilder setCreated_at(Date created_at) {
            this.created_at = created_at;
            return this;
        }
        public ArticleDtoBuilder setUpdated_at(Date updated_at) {
            this.updated_at = updated_at;
            return this;
        }
        public  ArticleDto build() {
            return new ArticleDto(id,title,text, status,user,created_at,updated_at);
        }
    }
}







