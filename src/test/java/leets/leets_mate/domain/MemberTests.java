package leets.leets_mate.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberTests {

    @Test
    void 입력받은_문자열을_파싱하여_리스트로_만든다() {
        String[] members = {"리츠에", "오신", "걸", "환영합니다"};
        List<Member> actual = Member.parseMembers(members);
        assertThat(actual).extracting(Member::getName)
                .containsExactly("리츠에", "오신", "걸", "환영합니다");    }

    @Test
    void 멤버수를_반환한다() {
        String[] membersArray = {"리츠에", "오신", "걸", "환영합니다"};
        List<Member> members = Member.parseMembers(membersArray);
        int actual = Member.memberNumber(members);
        assertThat(actual).isEqualTo(4);
    }
}

