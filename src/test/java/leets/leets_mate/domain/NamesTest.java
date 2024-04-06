package leets.leets_mate.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NamesTest {

    @Test
    @DisplayName("이름 수를 반환한다.")
    void countNames() {
        Names names = new Names(List.of(
                new Name("아연"),
                new Name("나아연"),
                new Name("나나아연"),
                new Name("나나나아연")
        ));

        assertThat(names.countNames()).isEqualTo(4);
    }
}