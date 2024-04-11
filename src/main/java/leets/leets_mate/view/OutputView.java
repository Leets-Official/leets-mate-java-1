package leets.leets_mate.view;

import org.w3c.dom.ls.LSOutput;

import java.util.List;

public class OutputView {
    public static void printStartMessage() {
        System.out.println("[Leets 오늘의 짝에게]를 시작합니다.");
        System.out.println();
        System.out.println("멤버의 이름을 입력해 주세요. (, 로 구분)");
    }

    public static void printMaxGroup() {
        System.out.println("최대 짝 수를 입력해 주세요.");
    }
    // 결과를 프린트 하는 함수입니다.
    public void printResult(List<List<String>> result) {
    }

}
