package leets.leets_mate.domain;

public class MaxGroupSize {

    int maxGroupSize;
    public MaxGroupSize(int maxGroupSize) {
        this.maxGroupSize = maxGroupSize;
    }

    public void checkMemberCountBelowMaxGroupSize(int memberCount) {
        if (memberCount < maxGroupSize) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }
}
