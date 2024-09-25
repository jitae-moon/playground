package com.example.playground.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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

    @Setter
    @Column(length = 1000)
    private String title;

    @Setter
    @Column(length = 50000)
    private String content;

    @ToString.Exclude
    @OneToMany(mappedBy = "article")
    private Set<ArticleComment> articleComments = new LinkedHashSet<>();

    protected Article() { }

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
