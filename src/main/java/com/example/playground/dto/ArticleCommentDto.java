package com.example.playground.dto;

import lombok.Builder;

public record ArticleCommentDto(
        Long id,
        String content
) {

    @Builder
    public ArticleCommentDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }

}
