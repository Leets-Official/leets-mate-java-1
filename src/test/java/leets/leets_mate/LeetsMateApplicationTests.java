package leets.leets_mate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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
    void parseMembers() {
        String members = "리츠에,오신,걸,환영합니다";
        List<String> actual = app.parseMembers(members);
        assertThat(actual).containsExactly("리츠에", "오신", "걸", "환영합니다");
    }

    @Test
    void memberNumber() {
        List<String> members = Arrays.asList("리츠에", "오신", "걸", "환영합니다");
        int actual = app.memberNumber(members);
        assertThat(actual).isEqualTo(4);
    }

    @Test
    void checkHasNoEnglish() {
        assertThrows(Exception.class, () -> {
            app.checkDataValidity(3, 4);
        });
    }

    @Test
    void checkDataValidity() {
        assertThrows(Exception.class, () -> {
            app.checkHasNoEnglish("welcome,to,leets");
        });
    }
}