package leets.leets_mate.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberTests {
    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member();
    }

    @Test
    void 입력받은_문자열을_파싱하여_리스트로_만든다() {
        String members = "리츠에,오신,걸,환영합니다";
        List<String> actual = member.parseMembers(members);
        assertThat(actual).containsExactly("리츠에", "오신", "걸", "환영합니다");
    }

    @Test
    void 멤버수를_반환한다() {
        List<String> members = Arrays.asList("리츠에", "오신", "걸", "환영합니다");
        int actual = member.memberNumber(members);
        assertThat(actual).isEqualTo(4);
    }
}

