package com.kryvapust.articlesblog.model;

import com.kryvapust.articlesblog.model.enums.ArticleStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder(setterPrefix = "set")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor

@Entity
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "articles_tags",
            joinColumns = {@JoinColumn(name = "article_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")})
    private Set<Tag> tags;

    @Override
    public String toString() {
        return title;
    }
}
