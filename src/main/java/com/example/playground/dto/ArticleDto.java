package com.example.playground.dto;

import com.example.playground.domain.Article;
import lombok.Builder;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleDto(
        Long id,
        String title,
        String content,
        Set<ArticleCommentDto> articleComments
) {

    @Builder
    public ArticleDto(Long id, String title, String content, Set<ArticleCommentDto> articleComments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.articleComments = articleComments;
    }

    public static ArticleDto from(Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .articleComments(article.getArticleComments().stream()
                        .map(ArticleCommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)))
                .build();
    }

    public Article toEntity() {
        return Article.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }

}
