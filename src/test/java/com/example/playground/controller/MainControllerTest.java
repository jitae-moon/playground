package com.example.playground.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MainControllerTest {

    @Value("${custom.word}")
    String word;

    @DisplayName("Test property 가져오는 테스트")
    @Test
    void testWord() {
        assertThat(word).isEqualTo("hello");
    }

}
