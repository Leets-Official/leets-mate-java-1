package leets.leets_mate.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner kb = new Scanner(System.in);

    private InputView() {
    }

    public static InputView create() {
        return new InputView();
    }

    public String readMember() {
        return "";
    }

    // 멤버 문자열에 영어가 있는지 검사합니다. 영어가 있다면 예외 출력
    public void checkHasNoEnglish(String members) {
    }

    // 멤버수와 최대 짝수 데이터가 유효한지 검사하는 함수입니다. 유효하지 않다면 예외 출력
    public void checkDataValidity(int memberCount, int maximumGroupSize) {
    }
}
