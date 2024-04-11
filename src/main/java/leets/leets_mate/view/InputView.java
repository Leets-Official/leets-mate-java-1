package leets.leets_mate.view;

import leets.leets_mate.view.exception.IllegalArgumentExceptionType;

import java.util.InputMismatchException;
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
        OutputView.printStartMessage();
        try {
            String input = kb.nextLine();
            String[] members = input.split(",");
            for (String member : members) {
                checkHasNoEnglish(member);
            }
            return members;
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // 추후 이부분은 enum으로 동아리 부원를 관리했을 때 없는 동아리 부원을 입력하면 에러메시지 출력도 하도록 구현
            return readMember();
        }
    }

    public static int readMaxGroup(String[] members) {
        OutputView.printMaxGroup();
        try {
            int memberCount = members.length;
            int maximumGroupSize = kb.nextInt();
            kb.nextLine();

            checkDataValidity(memberCount, maximumGroupSize);

            System.out.println();
            return maximumGroupSize;
        } catch (InputMismatchException e) {
            System.out.println(IllegalArgumentExceptionType.INVALID_MAX_GROUP.getMessage());
            kb.nextLine();
            return readMaxGroup((members));
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return readMaxGroup(members);
        }
    }

    public static void checkHasNoEnglish(String member) {
        // Character.isAlphabetic()은 영어인지 구별해주는게 아닌 숫자와 문자를 구분해준다!
//        for(char c : member.toCharArray()) {
//            if (Character.isAlphabetic(c)) {
//                throw IllegalArgumentExceptionType.INVALID_NAMING.getException();
//            }
//        }
        if (Pattern.matches("^[a-zA-Z]*$", member)) {
            throw IllegalArgumentExceptionType.INVALID_NAMING.getException();
        }
    }

    public static void checkDataValidity(int memberCount, int maximumGroupSize) {
        if (memberCount < maximumGroupSize) {
            throw IllegalArgumentExceptionType.GROUP_SIZE_EXCEEDS_MEMBER_COUNT.getException();
        }
    }

    public static boolean readRetry() {
        try {
            char c = kb.next().charAt(0);
            if (c == 'y') {
                return true;
            } else if (c == 'n') {
                return false;
            } else
                throw IllegalArgumentExceptionType.INVALID_RETRY_INPUT.getException();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readRetry();
        }
    }
}
