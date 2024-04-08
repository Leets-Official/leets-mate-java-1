package leets.leets_mate.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PairMatcherTest {

    private final Name name1 = new Name("아연");
    private final Name name2 = new Name("나아연");
    private final Name name3 = new Name("나나아연");
    private final Name name4 = new Name("나나나아연");
    private Names names;

    @BeforeEach
    void setUp() {

        names = new Names(
                List.of(name1, name2, name3, name4)
        );
    }

    @Test
    @DisplayName("나머지 없이 랜덤으로 짝을 매치한다.")
    void pairMatcher_Multiple() {
        PairMatcher pairMatcher = new PairMatcher(new PairCount(2), new RandomNumbersGenerator() {

            @Override
            public RandomNumbers randomNumbers(int bound) {
                return new RandomNumbers(List.of(0, 3, 1, 2));
            }
        });

        List<Pair> pairs = pairMatcher.match(names);

        assertThat(pairs).containsOnly(
                new Pair(List.of(name1, name4)),
                new Pair(List.of(name2, name3))
        );
    }

    @Test
    @DisplayName("나머지가 있어도 랜덤으로 짝을 매치한다.")
    void pairMatcher_overNamesCount_ExceptionThrown() {
        PairMatcher pairMatcher = new PairMatcher(new PairCount(3), new RandomNumbersGenerator() {

            @Override
            public RandomNumbers randomNumbers(int bound) {
                return new RandomNumbers(List.of(0, 3, 1, 2));
            }
        });

        List<Pair> pairs = pairMatcher.match(names);

        assertThat(pairs).containsOnly(
                new Pair(List.of(name1, name4, name2)),
                new Pair(List.of(name3))
        );
    }
}
