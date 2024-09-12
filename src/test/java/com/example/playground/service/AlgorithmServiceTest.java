package com.example.playground.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = AlgorithmService.class)
class AlgorithmServiceTest {

    AlgorithmService sut;

    @Autowired
    public AlgorithmServiceTest(AlgorithmService sut) {
        this.sut = sut;
    }

    @MethodSource
    @ParameterizedTest
    void testBubbleSort(List<Integer> unorderedList, List<Integer> expected) {
        List<Integer> actual = sut.bubbleSort(unorderedList);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testBubbleSort() {
        return Stream.of(
                Arguments.of(Arrays.asList(5, 4, 3, 2, 1), List.of(1, 2, 3, 4, 5)),
                Arguments.of(Arrays.asList(Integer.MAX_VALUE, Integer.MIN_VALUE, 0), List.of(Integer.MIN_VALUE, 0, Integer.MAX_VALUE)),
                Arguments.of(Arrays.asList(1, 1, 1, 1, 1, 1, 2, 1, 1, 1), List.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 2))
        );
    }

    @MethodSource
    @ParameterizedTest
    void testPermutation(int n, int m, List<int[]> expected) {
        List<int[]> actual = sut.permutation(n, m);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testPermutation() {
        return Stream.of(
                Arguments.of(4, 2, List.of(
                        new int[]{1, 2}, new int[]{1, 3}, new int[]{1, 4},
                        new int[]{2, 1}, new int[]{2, 3}, new int[]{2, 4},
                        new int[]{3, 1}, new int[]{3, 2}, new int[]{3, 4},
                        new int[]{4, 1}, new int[]{4, 2}, new int[]{4, 3}
                ))
        );
    }

}
