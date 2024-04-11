package leets.leets_mate.view;

import java.util.List;

public class Prints {
    public void opening() {
        System.out.println("[Leets 오늘의 짝에게]를 시작합니다.");
        System.out.println();
    }

    public void result(List<List<String>> matchedGroups) {
        System.out.println();
        System.out.println("오늘의 짝 추천 결과입니다.");

        for (List<String> group : matchedGroups) {
            System.out.print("[ ");
            for (int i = 0; i < group.size(); i++) {
                System.out.print(group.get(i));
                if (i < group.size() - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println(" ]");
        }
        System.out.println();
        System.out.println("추천을 완료했습니다.");
        System.out.println();
    }

    public void line() {
        System.out.println("--------------------------------");
    }

    public void ending() {
        System.out.print("자리를 이동해 서로에게 인사해주세요.");
    }
}