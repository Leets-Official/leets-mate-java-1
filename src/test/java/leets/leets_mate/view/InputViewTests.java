package leets.leets_mate.view;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputViewTests {

    @Test
    void 멤버수와_최대_멤버수를_잘못_입력한_경우_예외를_반환한다() {
        InputView inputView = InputView.create();
        assertThrows(IllegalArgumentException.class, () -> inputView.checkDataValidity(3, 4));
    }

    @Test
    void 멤버_문자열에_영어를_입력한_경우_예외를_반환한다() {
        InputView inputView = InputView.create();
        assertThrows(IllegalArgumentException.class, () -> inputView.checkHasNoEnglish("welcome,to,leets"));
    }
}
