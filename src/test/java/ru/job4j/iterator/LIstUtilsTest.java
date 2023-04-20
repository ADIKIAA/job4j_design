package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        input.addAll(List.of(2, 4, 5, 6, 8));
        Predicate predicate =  t -> (Integer) t % 2 == 0;
        ListUtils.removeIf(input, predicate);
        assertThat(input).containsExactly(1, 3, 5).hasSize(3);

    }

    @Test
    void whenRemoveIfListIsEmpty() {
        input = List.of();
        Predicate predicate = t -> (Integer) t == 1;
        ListUtils.removeIf(input, predicate);
    }

    @Test
    void whenReplaceIf1To7() {
        input.addAll(List.of(2, 1, 4, 1, 1));
        Predicate predicate = t -> (Integer) t == 1;
        ListUtils.replaceIf(input, predicate, 7);
        assertThat(input).doesNotContain(1);
    }

    @Test
    void whenReplaceIf3To5() {
        input.addAll(List.of(5, 3, 3));
        Predicate predicate = t -> (Integer) t == 3;
        ListUtils.replaceIf(input, predicate, 5);
        assertThat(input).doesNotContain(3).hasSize(5);
    }

    @Test
    void whenRemoveAll() {
        input.addAll(List.of(2, 3, 4, 5));
        ListUtils.removeAll(input, List.of(3, 5));
        assertThat(input).hasSize(3).containsExactly(1, 2, 4);
    }

    @Test
    void whenRemoveIfThenListIsEmpty() {
        ListUtils.removeAll(input, List.of(3, 1));
        assertThat(input).isEmpty();
    }

    @Test
    void whenRemoveAllListIsEmpty() {
        ListUtils.removeAll(input, List.of());
        assertThat(input).containsExactly(1, 3).hasSize(2);
    }
}