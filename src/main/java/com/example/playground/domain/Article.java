package com.example.playground.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;

@ToString(callSuper = true)
@Getter
@Entity
public class Article extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ToString.Exclude
    @OneToMany
    private Set<ArticleComment> articleComments = new LinkedHashSet<>();

    protected Article() { }

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
