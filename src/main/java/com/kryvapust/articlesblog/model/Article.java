package com.kryvapust.articlesblog.model;

import com.kryvapust.articlesblog.model.enums.ArticleStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private User user;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date created;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updated;

    @Override
    public String toString() {
        return title;
    }
}
