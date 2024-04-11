package leets.leets_mate.view;

import leets.leets_mate.view.exception.IllegalArgumentExceptionType;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    private static final Scanner kb = new Scanner(System.in);

    private InputView() {
    }

    public static InputView create() {
        return new InputView();
    }

    public static String[] readMember() {
        try {
            String input = kb.nextLine();
            String[] members = input.split(",");
            for (String member : members) {
                checkHasNoEnglish(member);
            }
            return members;
        }
        catch (IllegalArgumentException e) {
//            System.out.println(""); 추후 이부분은 enum으로 동아리 부원를 관리했을 때 없는 동아리 부원을 입력하면 에러메시지 출력
            return readMember();
        }
    }

    public static int readMaxGroup() {
        try {
            int memberCount = readMember().length;
            int maximumGroupSize = kb.nextInt();

            checkDataValidity(memberCount, maximumGroupSize);

            return memberCount;
        } catch (IllegalArgumentException e){
            System.out.println(IllegalArgumentExceptionType.INVALID_MAX_GROUP.getMessage());
            return readMaxGroup();
        }
    }

    // 멤버 문자열에 영어가 있는지 검사합니다. 영어가 있다면 예외 출력
    public static void checkHasNoEnglish(String member) {
        // Character.isAlphabetic()은 영어인지 구별해주는게 아닌 숫자와 문자를 구분해준다!
//        for(char c : member.toCharArray()) {
//            if (Character.isAlphabetic(c)) {
//                throw IllegalArgumentExceptionType.INVALID_NAMING.getException();
//            }
//        }
        if (!Pattern.matches("^[a-zA-Z]*$", member)) {
            throw IllegalArgumentExceptionType.INVALID_NAMING.getException();
        }
    }

    // 멤버수와 최대 짝수 데이터가 유효한지 검사하는 함수입니다. 유효하지 않다면 예외 출력
    public static void checkDataValidity(int memberCount, int maximumGroupSize) {
        if (memberCount > maximumGroupSize) {
            throw IllegalArgumentExceptionType.GROUP_SIZE_EXCEEDS_MEMBER_COUNT.getException();
        }
    }
}
