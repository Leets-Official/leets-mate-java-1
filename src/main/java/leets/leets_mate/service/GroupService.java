package leets.leets_mate.service;

import leets.leets_mate.domain.Member;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupService {
    public List<List<Member>> generateRandomGroups(List<Member> memberList, int maximumGroupSize) {
        Collections.shuffle(memberList);

        List<List<Member>> groups = new ArrayList<>();
        List<Member> group = new ArrayList<>();

        for (Member member : memberList) {
            group.add(member);

            if (group.size() == maximumGroupSize) {
                groups.add(new ArrayList<>(group));
                group.clear();
            }
        }

        // 현재로써는 빈 그룹이 추가될 가능성이 보이지 않긴하나, 혹시 모를 빈 그룹 추가 방지
        if (!group.isEmpty()) {
            groups.add(group);
        }

        return groups;
    }
}
