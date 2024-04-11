package leets.leets_mate.view;

import leets.leets_mate.domain.Member;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printStartMessage() {
        System.out.println("[Leets 오늘의 짝에게]를 시작합니다.");
        System.out.println();
        System.out.println("멤버의 이름을 입력해 주세요. (, 로 구분)");
    }

    public static void printMaxGroup() {
        System.out.println();
        System.out.println("최대 짝 수를 입력해 주세요.");
    }

    // 결과를 프린트 하는 함수입니다.
    public static void printResult(List<List<Member>> result) {
        System.out.println();
        System.out.println("오늘의 짝 추천 결과입니다.");
        for (List<Member> group : result) {

            List<String> memberNames = group.stream()
                    .map(Member::getName)
                    .collect(Collectors.toList());
            String groupResult = String.join(" | ", memberNames);

            System.out.println("[ " + groupResult + " ]");
        }
    }

    public static void printCompletionMessage() {
        System.out.println();
        System.out.println("추천을 완료했습니다.");
    }
}
