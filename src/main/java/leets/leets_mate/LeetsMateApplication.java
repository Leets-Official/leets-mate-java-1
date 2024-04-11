package leets.leets_mate;

import java.util.*;

public class LeetsMateApplication {

    // 동작 함수입니다.
    public void run() throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("[Leets 오늘의 짝에게]를 시작합니다.\n");
        System.out.println("멤버의 이름을 입력해 주세요. (, 로 구분)");
        String members = sc.nextLine();

        checkHasNoEnglish(members);
        List<String> memberList = parseMembers(members);
        System.out.println();
        System.out.println("최대 짝 수를 입력해 주세요.");


        int maximumGroupSize = Integer.parseInt(sc.nextLine());
        int memberCount = memberNumber(memberList);

        checkDataValidity(memberCount, maximumGroupSize);

        List<List<String>> lists = generateRandomGroups(memberList, maximumGroupSize);

        System.out.println();
        System.out.println("오늘의 짝 추천 결과입니다.");
        printResult(lists);

        System.out.println("\n추첨을 완료하였습니다.");

        while (askForRestart(sc)) {
            System.out.println("--------------------------------");
            lists = generateRandomGroups(memberList, maximumGroupSize);
            System.out.println();
            System.out.println("오늘의 짝 추천 결과입니다.");
            printResult(lists);
            System.out.println("\n추첨을 완료하였습니다.");
        }
        sc.close();


    }

    // 문자열로된 멤버들을 리스트로 분리하는 함수입니다.
    public List<String> parseMembers(String members) {
        return Arrays.asList(members.split(","));
    }

    // 총 멤버수를 반환합니다.
    public int memberNumber(List<String> members) {
        return members.size();
    }

    // 멤버 문자열에 영어가 있는지 검사합니다. 영어가 있다면 예외 출력
    public void checkHasNoEnglish(String members) throws Exception {
        if (members.matches(".*[a-zA-Z].*")) {
            throw new Exception("[ERROR] 이름은 한글로 입력해야 합니다");
        }
    }

    // 멤버수와 최대 짝수 데이터가 유효한지 검사하는 함수입니다. 유효하지 않다면 예외 출력
    public void checkDataValidity(int memberCount, int maximumGroupSize) throws Exception {
        if (maximumGroupSize > memberCount) {
            throw new Exception("[ERROR] 최대 짝 수는 이름의 갯수보다 클 수 없습니다");
        }
    }

    // 랜덤 짝꿍 추첨하는 함수 입니다.
    public List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {
        Collections.shuffle(memberList);

        List<List<String>> resultList = new ArrayList<>();

        int colum = maximumGroupSize; //4
        int row; //2

        if (memberList.size() % maximumGroupSize == 0) {
            row = memberList.size() / maximumGroupSize;
        } else {
            row = memberList.size() / maximumGroupSize + 1;
        }


        for (int i = 0; i < row; i++) {
            resultList.add(new ArrayList<>());
        }

        for (int i = 0; i < row; i++) {
            for (int j = i*colum; j < colum*(i+1); j++) {
                if(j<memberList.size()) resultList.get(i).add(memberList.get(j));
            }
        }
        return resultList;
    }

    // 결과를 프린트 하는 함수입니다.
    public void printResult(List<List<String>> result) {
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<result.size(); i++){
            sb.append("[ ");
            for(int j=0; j<result.get(i).size(); j++){
                sb.append(result.get(i).get(j));
                if(j<result.get(i).size()-1) sb.append(" | ");
            }
            sb.append(" ]");
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public boolean askForRestart(Scanner sc) {
        while (true) {
            System.out.print("다시 구성하시겠습니까? (y or n): ");
            String retry = sc.nextLine();
            if (retry.equals("n")) {
                System.out.println("자리를 이동해 서로에게 인사해주세요.");
                return false;
            } else if (retry.equals("y")) {
                return true;
            } else {
                System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        LeetsMateApplication app = new LeetsMateApplication();
        app.run();
    }
}