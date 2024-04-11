package leets.leets_mate;

import leets.leets_mate.domain.MaxGroupSize;
import leets.leets_mate.domain.Members;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LeetsMateApplicationTests {
    LeetsMateApplication app;

    @BeforeEach
    void setUp() {
        app = new LeetsMateApplication();
        Members members = new Members("나는,양태석,이다");
        MaxGroupSize maxGroupSize = new MaxGroupSize(2);
    }

    @Test
    void Members_객체_생성() {
        Members members = new Members("나는,양태석,이다");
        assertThat(members.membersList).containsExactly("나는", "양태석", "이다");
    }

    @Test
    void 멤버수를_반환한다() {
        Members members = new Members("나는,양태석,이다");
        int actual = members.getCount();
        assertThat(actual).isEqualTo(3);
    }

    @Test
    void 멤버수와_최대_멤버수를_잘못_입력한_경우_예외를_반환한다() {
        Members members = new Members("나는,양태석,이다");
        MaxGroupSize maxGroupSize = new MaxGroupSize(4);
        assertThrows(Exception.class, () -> {
            maxGroupSize.checkMemberCountBelowMaxGroupSize(members.getCount());
        });
    }

    @Test
    void 멤버_문자열에_영어를_입력한_경우_예외를_반환한다() {
        assertThrows(Exception.class, () -> {
            Members members = new Members("hello");
        });
    }
}