package leets.leets_mate.service;

import leets.leets_mate.domain.Member;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupService {
    // 랜덤 짝꿍 추첨하는 함수 입니다.
    public List<List<Member>> generateRandomGroups(List<Member> memberList, int maximumGroupSize) {
        Collections.shuffle(memberList);

        return new ArrayList<>();
    }
}
