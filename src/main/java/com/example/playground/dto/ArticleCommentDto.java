package com.example.playground.dto;

import com.example.playground.domain.ArticleComment;
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

    public static ArticleCommentDto from(ArticleComment articleComment) {
        return ArticleCommentDto.builder()
                .id(articleComment.getId())
                .content(articleComment.getContent())
                .build();
    }

}
