package com.kryvapust.articlesblog.model;

import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "articles")
@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
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
    private Status status;

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
