package com.example.playground.dto;

import lombok.Builder;

import java.util.Set;

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

}
