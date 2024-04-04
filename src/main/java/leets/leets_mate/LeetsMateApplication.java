package leets.leets_mate;

import java.util.*;

public class LeetsMateApplication {

    static String members;
    static int row;

    // 동작 함수입니다.
    public void run() throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("[Leets 오늘의 짝에게]를 시작합니다.\n");

        System.out.println("참석자들의 이름을 입력해 주세요. (,로 구분)");
        members = sc.nextLine();

        checkHasNoEnglish(members);

        System.out.println("\n최대 짝 수를 입력해 주세요.");
        int maximumGroupSize = sc.nextInt();

        List<String> memberList = parseMembers(members);

        checkDataValidity(memberNumber(memberList), maximumGroupSize);

        printResult(generateRandomGroups(memberList, maximumGroupSize));

        while (true) {
            System.out.print("다시 구성하시겠습니까? (y or n): ");
            String answer = sc.next();

            if (answer.equals("y")) {
                printResult(generateRandomGroups(memberList, maximumGroupSize));
            } else {
                System.out.println("자리를 이동해 서로에게 인사해주세요.");
                break;
            }
        }
    }

    // 문자열로된 멤버들을 리스트로 분리하는 함수입니다.
    public List<String> parseMembers(String members) {
        List<String> memberList = Arrays.stream(members.split(",")).toList();
        // toList를 하게 되면 변경할 수 없는 리스트가 되기 때문에 다시 변경 가능한 리스트로 변환한다.
        return new ArrayList<>(memberList);
    }

    // 총 멤버수를 반환합니다.
    public int memberNumber(List<String> members) {
        return members.size();
    }

    // 멤버 문자열에 영어가 있는지 검사합니다. 영어가 있다면 예외 출력
    public void checkHasNoEnglish(String members) throws Exception {
        String[] membersArr = members.split(",");
        for (String member : membersArr) {
            if (member.matches("^[a-zA-Z]*$")) {
                throw new Exception("[ERROR] 이름은 한글로 입력해야 합니다");
            }
        }
    }

    // 멤버수와 최대 짝수 데이터가 유효한지 검사하는 함수입니다. 유효하지 않다면 예외 출력
    public void checkDataValidity(int memberCount, int maximumGroupSize) throws Exception {
        if (memberCount < maximumGroupSize) {
            throw new Exception("[ERROR] 최대 짝 수는 이름의 갯수보다 클 수 없습니다");
        }
    }

    // 랜덤 짝꿍 추첨하는 함수 입니다.
    public List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {
        Collections.shuffle(memberList);

        List<List<String>> result = new ArrayList<>();

        if (memberList.size() % maximumGroupSize != 0) {
            row = memberList.size() / maximumGroupSize + 1;
        } else {
            row = memberList.size() / maximumGroupSize;
        }

        for (int i = 0; i < row; i++) {
            result.add(new ArrayList<>());
        }

        for (int i = 0; i < row; i++) {
            for (int j = i * maximumGroupSize; j < maximumGroupSize * (i + 1); j++) {
                if (j < memberList.size()) {
                    result.get(i).add(memberList.get(j));
                }
            }
        }
        return result;
    }

    // 결과를 프린트 하는 함수입니다.
    public void printResult(List<List<String>> result) {
        System.out.println("\n오늘의 짝 추천 결과입니다.");

        StringBuilder sb = new StringBuilder();

        int column = result.get(0).size();

        for (int i = 0; i < row; i++) {
            sb.append("[ ");
            for (int j = 0; j < column; j++) {
                sb.append(result.get(i).get(j));

                if (i == row - 1 && j == members.length() % column) {
                    break;
                }

                if (j + 1 != column) {
                    sb.append(" | ");
                }
            }
            sb.append(" ]\n");
        }
        sb.append("\n추천을 완료했습니다.\n");
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        LeetsMateApplication app = new LeetsMateApplication();
        app.run();
    }
}