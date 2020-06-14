package com.kryvapust.articlesblog.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)

@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "message")
    private String message;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    private Article article;

    @Column(name = "post_id")
    private Integer articleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "author_id")
    private Integer userId;

    @Column(name = "created_at", insertable = false)
    private Date created;
}
