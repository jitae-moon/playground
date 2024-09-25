package com.example.playground.service;

import com.example.playground.domain.Article;
import com.example.playground.domain.type.SearchType;
import com.example.playground.dto.ArticleDto;
import com.example.playground.repository.ArticleCommentRepository;
import com.example.playground.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks ArticleService sut;

    @Mock ArticleRepository articleRepository;
    @Mock ArticleCommentRepository articleCommentRepository;

    @DisplayName("검색 타입, 검색 내용이 주어졌을 때 페이지로 정렬해서 반환하는 테스트.")
    @Test
    void givenSearchTypeAndSearchValue_whenSearchingArticles_thenReturnsPages() {
        // Given
        SearchType searchType = SearchType.TITLE;
        String searchValue = "Test";
        given(articleRepository.findByTitleContaining(anyString(), any(Pageable.class)))
                .willReturn(Page.empty());

        // When
        Page<ArticleDto> actual = sut.getArticles(searchType, searchValue, Pageable.ofSize(1));

        // Then
        assertThat(actual).isNotNull();
        assertThat(actual).size().isEqualTo(0);
        then(articleRepository).should().findByTitleContaining(anyString(), any(Pageable.class));
        then(articleRepository).should(times(0)).findByContentContaining(anyString(), any(Pageable.class));
    }

    @DisplayName("게시글 번호가 주어지면 해당 게시글 상세 내용을 반환하는 테스트.")
    @Test
    void givenArticleId_whenGettingArticle_thenReturnsArticleDetails() {
        // Given
        Long id = 1L;
        Article article = createArticle();
        given(articleRepository.findById(anyLong()))
                .willReturn(Optional.of(article));

        // When
        ArticleDto actual = sut.getArticle(id);

        // Then
        then(articleRepository).should().findById(anyLong());
        assertThat(actual).isNotNull();
    }

    @DisplayName("게시글 정보가 주어지면 해당 게시글을 저장하는 테스트.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticleAndReturnsArticleInfo() {
        // Given
        Article article = createArticle();
        ArticleDto articleDto = ArticleDto.from(article);
        given(articleRepository.save(any(Article.class)))
                .willReturn(article);

        // When
        ArticleDto actual = sut.saveArticle(articleDto);

        // Then
        assertThat(actual).isNotNull();
        assertThat(actual.title()).isEqualTo(articleDto.title());
        assertThat(actual.content()).isEqualTo(articleDto.content());
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글 id와 본문 수정 정보가 주어지면 게시글을 수정하는 테스트.")
    @Test
    void givenArticleIdAndInfo_whenUpdatingArticle_thenUpdatesArticleAndReturnsArticleDto() {
        // Given
        Long id = 1L;
        Article article = createArticle();
        ArticleDto articleDto = ArticleDto.builder()
                .id(1L)
                .content("Changed content")
                .build();
        given(articleRepository.findById(anyLong()))
                .willReturn(Optional.of(article));

        // When
        ArticleDto actual = sut.updateArticle(id, articleDto);

        // Then
        assertThat(actual).isNotNull();
        assertThat(actual.content()).isEqualTo(articleDto.content());
        then(articleRepository).should().findById(anyLong());
    }

    @DisplayName("게시글 id가 주어지면 해당 게시글을 삭제하는 테스트.")
    @Test
    void givenArticleId_whenDeletingId_thenDeletesId() {
        // Given
        Long id = 1L;
        Article article = createArticle();
        given(articleRepository.findById(anyLong()))
                .willReturn(Optional.of(article));
        willDoNothing().given(articleRepository).deleteById(id);

        // When & Then
        sut.deleteArticle(id);
        then(articleRepository).should().deleteById(anyLong());
    }

    private static Article createArticle() {
        return Article.builder()
                .title("Test title")
                .content("Test content")
                .build();
    }

}
