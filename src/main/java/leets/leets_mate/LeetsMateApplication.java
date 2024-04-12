package leets.leets_mate;

import java.util.*;

public class LeetsMateApplication {
    Scanner scanner = new Scanner(System.in);
    List<List<String>> result;
    List<String> memberList = new ArrayList<>();
    int maximumGroupSize;
    // 동작 함수입니다.
    public void run() {
        System.out.println("[Leets 오늘의 짝에게]를 시작합니다.");
        System.out.println("멤버의 이름을 입력해 주세요. (, 로 구분) ");
        String memberCount = scanner.nextLine();
        List<String> memberList = new ArrayList<>();
        Collections.addAll(memberList, memberCount.split(","));

        System.out.println("최대 짝 수를 입력해 주세요.");
        int maximumGroupSize = Integer.parseInt(scanner.nextLine());
        checkDataValidity(memberList.size(), maximumGroupSize);

        List<List<String>> result = generateRandomGroups(memberList, maximumGroupSize);
        printResult(result);
        reshuffle();
    }
    public void reshuffle() {
        while (true) {
            System.out.print("다시 구성하시겠습니까? (y or n): ");
            String s = scanner.nextLine();

            if (s.equalsIgnoreCase("n")) {
                System.out.println("자리 이동 후 서로 인사해주세요~");
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if(s.equalsIgnoreCase("y")){
                result = generateRandomGroups(memberList, maximumGroupSize);
                printResult(result);
            }
            else{
                throw new RuntimeException("[ERROR] y나 n을 입력해주세요");
            }
        }


    }

    // 총 멤버수를 반환합니다.
    public int memberNumber(List<String> members) {
        return 0;
    }

    // 멤버 문자열에 영어가 있는지 검사합니다. 영어가 있다면 예외 출력
    public void checkHasNoEnglish(String members) {
        if (!members.matches("[가-힣, ]+")) {
            throw new IllegalArgumentException("[ERROR] 이름은 한글로 입력해야 합니다");
        }
    }

    // 멤버수와 최대 짝수 데이터가 유효한지 검사하는 함수입니다. 유효하지 않다면 예외 출력
    public void checkDataValidity(int memberCount, int maximumGroupSize) {
        if (maximumGroupSize > memberCount) {
            throw new IllegalArgumentException("[ERROR] 최대 짝 수는 이름의 갯수보다 클 수 없습니다");
        }
    }

    // 랜덤 짝꿍 추첨하는 함수 입니다.
    public List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {
        Collections.shuffle(memberList);
        List<List<String>> result = new ArrayList<>();
        int currentIndex = 0;
        while (currentIndex < memberList.size()) {
            int groupSize = Math.min(maximumGroupSize, memberList.size() - currentIndex);
            List<String> group = memberList.subList(currentIndex, currentIndex + groupSize);
            result.add(group);
            currentIndex += groupSize;
        }
        return result;
    }

    // 결과를 프린트 하는 함수입니다.
    public void printResult(List<List<String>> result) {
        System.out.println("오늘의 짝 추천 결과입니다.");
        for (int i = 0; i < result.size(); i++) {
            List<String> group = result.get(i);
            System.out.println("[" + String.join("| ", group) + "]");
        }


    }

    public static void main(String[] args) {
        LeetsMateApplication app = new LeetsMateApplication();
        app.run();
    }
}