package leets.leets_mate;

import java.util.*;

public class LeetsMateApplication {
    String newline = System.lineSeparator();

    // 동작 함수입니다.
    public void run() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("[Leets 오늘의 짝에게]를 시작합니다.");

        System.out.println(newline + "참석자들의 이름을 입력해 주세요. (,로 구분)");
        String memberNamesInput = sc.nextLine();
        List<String> members = parseMembers(memberNamesInput);


        System.out.println(newline + "최대 짝 수를 입력해 주세요.");
        int maximumGroupSize = sc.nextInt();
        sc.nextLine(); // 개행문자 제거
        checkDataValidity(memberNumber(members), maximumGroupSize);
        System.out.println();

        boolean result = true;
        while (result) {
            printResult(generateRandomGroups(members, maximumGroupSize));

            System.out.print("다시 구성하시겠습니까? (y or n): ");
            String operator = sc.nextLine();

            result = isDone(operator);
        }

        System.out.println("자리를 이동해 서로에게 인사해주세요.");
        sc.close();
    }

    // 문자열로된 멤버들을 리스트로 분리하는 함수입니다.
    public List<String> parseMembers(String members) throws Exception {
        checkHasNoEnglish(members);
        return new ArrayList<>(List.of(members.split(",")));
    }

    // 총 멤버수를 반환합니다.
    public int memberNumber(List<String> members) {
        return members.size();
    }

    // 멤버 문자열에 영어가 있는지 검사합니다. 영어가 있다면 예외 출력
    public void checkHasNoEnglish(String members) throws Exception {
        if (members.matches(".*[a-zA-Z]+.*")) {
            throw new Exception("[ERROR] 이름은 한글로 입력해야 합니다.");
        }
    }

    // 멤버수와 최대 짝수 데이터가 유효한지 검사하는 함수입니다. 유효하지 않다면 예외 출력
    public void checkDataValidity(int memberCount, int maximumGroupSize) throws Exception {
        if (memberCount < maximumGroupSize) {
            throw new Exception("[ERROR] 최대 짝 수는 이름의 갯수보다 클 수 없습니다.");
        }
    }

    // 랜덤 짝꿍 추첨하는 함수 입니다.
    public List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {
        Collections.shuffle(memberList);

        List<List<String>> result = new ArrayList<>();
        List<String> currentGroup = new ArrayList<>();

        for (String member : memberList) {
            currentGroup.add(member);

            if (currentGroup.size() == maximumGroupSize) {
                result.add(new ArrayList<>(currentGroup));
                currentGroup.clear();
            }
        }

        if (!currentGroup.isEmpty()) {
            result.add(new ArrayList<>(currentGroup));
        }

        return result;
    }

    // 결과를 프린트 하는 함수입니다.
    public void printResult(List<List<String>> result) {
        System.out.println("오늘의 짝 추천 결과입니다.");

        StringBuilder sb = new StringBuilder();

        for (List<String> group : result) {
            StringJoiner sj = new StringJoiner(" | ", "[ ", " ]");

            for (String member : group) {
                sj.add(member);
            }

            sb.append(sj).append(newline);
        }

        sb.append(newline).append("추천을 완료했습니다.");
        System.out.println(sb);
    }

    // 다시 추천을 할지 물어보는 함수입니다.
    public boolean isDone(String operator) throws Exception {
        if (operator.equals("n")) {
            return false;
        }

        if (operator.equals("y")) {
            System.out.println("--------------------------------");
            return true;
        }

        throw new Exception("[ERROR] y 또는 n을 입력해주세요.");
    }

    public static void main(String[] args) throws Exception {
        LeetsMateApplication app = new LeetsMateApplication();
        app.run();
    }
}