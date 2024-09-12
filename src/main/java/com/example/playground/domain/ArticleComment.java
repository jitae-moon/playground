package com.example.playground.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Entity
public class ArticleComment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5000)
    private String content;

    @ManyToOne
    private Article article;

    protected ArticleComment() { }

    @Builder
    public ArticleComment(String content, Article article) {
        this.content = content;
        this.article = article;
    }

}
