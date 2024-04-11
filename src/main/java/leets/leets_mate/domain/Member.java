package leets.leets_mate.domain;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public static List<Member> parseMembers(String[] memberName) {
        List<Member> members = new ArrayList<>();
        for (String name : memberName) {
            members.add(new Member(name));
        }

        return members;
    }

    public static int memberNumber(List<Member> members) {
        return members.size();
    }

    public String getName() {
        return name;
    }
}
