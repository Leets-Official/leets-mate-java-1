package leets.leets_mate.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    // 문자열로된 멤버들을 리스트로 분리하는 함수입니다.
    public static List<Member> parseMembers(String[] memberName) {
        List<Member> members = new ArrayList<>();
        for (String name : memberName) {
            members.add(new Member(name));
        }

        return members;
    }

    // 총 멤버수를 반환합니다.
    public static int memberNumber(List<Member> members) {
        return members.size();
    }

    public String getName() {
        return name;
    }
}
