package leets.leets_mate.view;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    public void printStartNotice() {
        System.out.println("[Leets 오늘의 짝에게]를 시작합니다.");
    }

    public void printPairMatchResultNotice() {
        System.out.println();
        System.out.println("오늘의 짝 추천 결과입니다.");
    }

    public void printPairMatchResult(List<String> pairs) {
        StringJoiner stringJoiner = new StringJoiner(" | ", "[ ", " ]");
        for (String pair : pairs) {
            stringJoiner.add(pair);
        }
        System.out.println(stringJoiner);
    }

    public void printPairMatchFinishNotice() {
        System.out.println();
        System.out.println("추천을 완료했습니다.");
    }

    public void printBreakLine() {
        System.out.println("--------------------------------");
    }

    public void printEndNotice() {
        System.out.println("자리를 이동해 서로에게 인사해주세요.");
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
