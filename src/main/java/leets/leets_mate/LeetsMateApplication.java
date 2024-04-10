package leets.leets_mate;

import java.util.*;
import java.util.stream.Collectors;

public class LeetsMateApplication {

    private final Scanner scanner;
    public LeetsMateApplication() { this.scanner = new Scanner(System.in);}

    // 동작 함수
    public void run() {
        System.out.println("[Leets 오늘의 짝에게]를 시작 합니다. >!<");

        //Member 이름 받기
        List<String> members = getMembers();
        if (members == null) return;

        // 최대 짝 수 입력 받기
        int maximumGroupSize = getMaximumGroupSize(members.size());
        if (maximumGroupSize == -1) return;

        try {

            // 짝 결과 출력
            List<List<String>> result = generateRandomGroups(members, maximumGroupSize);
            printResult(result);

            // 다시하기
            if (Retry()) {
                run();
            } else {
                System.out.println("자리를 이동해 서로에게 인사해주세요. *^.^*");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            run();
        }
    }

    // 멤버의 이름 받기
    private List<String> getMembers() {
        List<String> memberList;
        while (true) {
            System.out.println("멤버의 이름을 입력해 주세요. (, 로 구분)");
            String input = scanner.nextLine();

            // 입력된 값이 한글이 아닌 경우
            if (containsEnglish(input)) {
                System.out.println("[ERROR] 영어가 포함되어 있습니다. 한글로 이름을 입력해주세요.");
                continue;
            }

            // 문자열 ","로 분리 + 한글 이름만 필터링으로 리스트 반환
            memberList = parseMembers(input);

            // 입력된 맴버가 없을 경우, 에러 메세지
            if (memberList.isEmpty()) {
                System.out.println("[ERROR] 입력된 값이 없습니다. 한글로 이름을 입력해주세요.");
                continue;
            }

            break;
        }
        return memberList;
    }

    // 입력된 문자열에 영어가 포함되어 있는지 확인하는 함수
    private boolean containsEnglish(String input) {
        return input.matches(".*[a-zA-Z].*");
    }

    // 문자열로된 멤버들을 리스트로 분리하는 함수입니다.
    private List<String> parseMembers(String membersString) {
        return Arrays.stream(membersString.split(", "))
                .filter(name -> name.matches("[가-힣]+"))
                .map(String::trim) // 각 멤버 문자열의 앞뒤 공백 제거
                .collect(Collectors.toList());
    }

    // 최대 짝 수를 입력
    private int getMaximumGroupSize(int memberCount) {
        int maximumGroupSize;
        while (true) {
            System.out.println("최대 짝 수를 입력해 주세요.");
            try {
                maximumGroupSize = scanner.nextInt();
                scanner.nextLine();

                // 최대 짝 수가 유효한 값이 아닌 경우, 에러 메세지 출력
                if (maximumGroupSize <= 0 || maximumGroupSize > memberCount) {
                    System.out.println("[ERROR] 최대 짝 수는 1 이상이고, 멤버 수 이하의 값이어야 합니다.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                // 정수가 아닌 값을 입력할 경우, 에러 메세지 출력
                System.out.println("[ERROR] 정수를 입력하세요.");
                scanner.nextLine();
            }
        }
        return maximumGroupSize;
    }

    // 랜덤 짝꿍 추첨 함수
    private List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {
        List<String> shuffledMembers = new ArrayList<>(memberList);
        Collections.shuffle(shuffledMembers);

        List<List<String>> groups = new ArrayList<>();
        for (int i = 0; i < shuffledMembers.size(); i += maximumGroupSize) {
            groups.add(shuffledMembers.subList(i, Math.min(i + maximumGroupSize, shuffledMembers.size())));
        }
        return groups;
    }

    // 결과를 출력 함수
    private void printResult(List<List<String>> result) {
        System.out.println("오늘의 짝 추천 결과 입니다:");
        result.forEach(System.out::println);
        System.out.println("추천을 완료했습니다.");
    }

    // "다시하기" 함수
    private boolean Retry() {
        System.out.println("다시 구성하시겠습니까? (y or n): ");
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("y");
    }

    public static void main(String[] args) {
        LeetsMateApplication app = new LeetsMateApplication();
        app.run();
    }
}
