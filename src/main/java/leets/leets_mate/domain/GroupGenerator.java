package leets.leets_mate.domain;

import java.util.ArrayList;
import java.util.List;

public class GroupGenerator {
    public List<List<String>> generateRandomGroups(Members members, MaxGroupSize maxGroupSize) {
        members.shuffleMembers();
        List<List<String>> matchedGroups = new ArrayList<>();
        for (int i = 0; i < members.getCount(); i += maxGroupSize.maxGroupSize) {
            matchedGroups.add(members.membersList.subList(i, Math.min(i + maxGroupSize.maxGroupSize, members.getCount())));
        }
        return matchedGroups;
    }
}ㄴ