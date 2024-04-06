package leets.leets_mate.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RandomNumbersGeneratorTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    @DisplayName("범위 내에서 지정 짝 수 크기와 같은 개수의 숫자들을 반환한다.")
    void randomNumbers(int pairCount) {
        RandomNumbersGenerator randomNumbersGenerator = new RandomNumbersGenerator();

        List<Integer> randomNumbers = randomNumbersGenerator.randomNumbers(pairCount, 5);

        assertThat(randomNumbers).hasSize(pairCount);
    }
}
