package leets.leets_mate.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RandomNumbersGeneratorTest {

    @Test
    @DisplayName("범위 내 숫자를 랜덤 순서로 반환한다.")
    void randomNumbers_pairCount_hasEqualSize() {
        RandomNumbersGenerator randomNumbersGenerator = new RandomNumbersGenerator();

        List<Integer> randomNumbers = randomNumbersGenerator.randomNumbers(10);

        assertThat(randomNumbers).containsOnly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }
}
