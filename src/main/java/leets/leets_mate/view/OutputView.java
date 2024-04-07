package leets.leets_mate.view;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    public void printStartNotice() {
        System.out.println("[Leets 오늘의 짝에게]를 시작합니다.");
        System.out.println();
    }

    public void printPairMatchNotice() {
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
}
