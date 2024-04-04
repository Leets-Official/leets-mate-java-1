package leets.leets_mate;

import java.util.*;

public class LeetsMateApplication {

    // 동작 함수입니다.
    public void run() {
        System.out.println("PR test");
    }

    // 문자열로된 멤버들을 리스트로 분리하는 함수입니다.
    public List<String> parseMembers(String members) {
        return new ArrayList<>();
    }

    // 총 멤버수를 반환합니다.
    public int memberNumber(List<String> members) {
        return 0;
    }

    // 멤버 문자열에 영어가 있는지 검사합니다. 영어가 있다면 예외 출력
    public void checkHasNoEnglish(String members) {
    }

    // 멤버수와 최대 짝수 데이터가 유효한지 검사하는 함수입니다. 유효하지 않다면 예외 출력
    public void checkDataValidity(int memberCount, int maximumGroupSize) {
    }

    // 랜덤 짝꿍 추첨하는 함수 입니다.
    public List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {
        return new ArrayList<>();
    }

    // 결과를 프린트 하는 함수입니다.
    public void printResult(List<List<String>> result) {
    }

    public static void main(String[] args) {
        LeetsMateApplication app = new LeetsMateApplication();
        app.run();
    }
}