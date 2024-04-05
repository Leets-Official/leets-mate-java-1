package leets.leets_mate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LeetsMateApplicationTests {
    LeetsMateApplication app;

    @BeforeEach
    void setUp() {
        app = new LeetsMateApplication();
    }

    @Test
    void 입력받은_문자열을_파싱하여_리스트로_만든다() {
        String members = "리츠에,오신,걸,환영합니다";
        List<String> actual = app.parseMembers(members);
        assertThat(actual).containsExactly("리츠에", "오신", "걸", "환영합니다");
    }

    @Test
    void 멤버수를_반환한다() {
        List<String> members = Arrays.asList("리츠에", "오신", "걸", "환영합니다");
        int actual = app.memberNumber(members);
        assertThat(actual).isEqualTo(4);
    }

    @Test
    void 멤버수와_최대_멤버수를_잘못_입력한_경우_예외를_반환한다() {
        Exception e = assertThrows(Exception.class, () -> {
            app.checkDataValidity(3, 4);
        });
        assertThat("[ERROR] 최대 짝 수는 이름의 갯수보다 클 수 없습니다.").isEqualTo(e.getMessage());
    }

    @Test
    void 멤버_문자열에_영어를_입력한_경우_예외를_반환한다() {
        InputMismatchException e = assertThrows(InputMismatchException.class, () -> {
            app.checkHasNoEnglish("welcome,to,leets");
        });
        assertThat("[ERROR] 이름은 한글로 입력해야 합니다.").isEqualTo(e.getMessage());
    }
}