package leets.leets_mate.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class NameTest {

    @Test
    @DisplayName("이름의 길이가 6글자 이상이라면 예외가 발생한다.")
    void name_InvalidLength_ExceptionThrown() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name("나아연이라능"));
    }
}
