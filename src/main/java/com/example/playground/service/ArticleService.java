package com.example.playground.service;

import com.example.playground.domain.Article;
import com.example.playground.domain.type.SearchType;
import com.example.playground.dto.ArticleDto;
import com.example.playground.repository.ArticleCommentRepository;
import com.example.playground.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public Page<ArticleDto> getArticles(SearchType searchType, String searchValue, Pageable pageable) {

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchValue, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchValue, pageable).map(ArticleDto::from);
        };
    }

    public ArticleDto getArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다. id = " + id));

        return ArticleDto.from(article);
    }

    public ArticleDto saveArticle(ArticleDto articleDto) {
        Article article = articleDto.toEntity();
        Article savedArticle = articleRepository.save(article);

        return ArticleDto.from(savedArticle);
    }

    public ArticleDto updateArticle(Long id, ArticleDto articleDto) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다. id = " + id));

        if (articleDto.title() != null) article.setTitle(articleDto.title());
        if (articleDto.content() != null) article.setContent(articleDto.content());

        return ArticleDto.from(article);
    }

    public void deleteArticle(Long id) {
        if (articleRepository.findById(id).isEmpty()) throw new EntityNotFoundException("게시글이 없습니다. 게시글 id = " + id);
        articleRepository.deleteById(id);
    }

}
