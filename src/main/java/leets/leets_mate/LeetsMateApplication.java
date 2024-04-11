package leets.leets_mate;

import java.util.*;

public class LeetsMateApplication {

    // 동작 함수입니다.
    public void run() {
        System.out.println("멤버의 이름을 입력해 주세요. (, 로 구분)");
        Scanner sc = new Scanner(System.in);
        String memberInput = sc.nextLine();
        checkHasNoEnglish(memberInput);
        List<String> memberList = parseMembers(memberInput);
        System.out.println("최대 짝 수를 입력해 주세요.");
        int maxNum = Integer.parseInt(sc.nextLine());
        checkDataValidity(memberList.size(), maxNum);
        Boolean isDone = true;
        while (isDone .equals(true)) {
            printResult(generateRandomGroups(memberList,maxNum));
            isDone = isContinue();
        }
    }

    // 문자열로된 멤버들을 리스트로 분리하는 함수입니다.
    public List<String> parseMembers(String members) {
        List<String> memberList = new ArrayList<>();
        for (String s : members.split(",")) {
            memberList.add(s);
        }
        return memberList;
    }

    // 총 멤버수를 반환합니다.
    public int memberNumber(List<String> members) {
        return members.size();
    }

    // 멤버 문자열에 영어가 있는지 검사합니다. 영어가 있다면 예외 출력
    public void checkHasNoEnglish(String members) {
        try {
            for (char c : members.toCharArray()) {
                if (!(c >= '가' && c <= '힣') && !(Character.toString(c).equals(","))) {
                    throw new Exception("[ERROR] 이름은 한글로 입력해야 합니다");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            run();
        }
    }

    // 멤버수와 최대 짝수 데이터가 유효한지 검사하는 함수입니다. 유효하지 않다면 예외 출력
    public void checkDataValidity(int memberCount, int maximumGroupSize) {
        try {
            if (memberCount < maximumGroupSize) {
                throw new Exception("[ERROR] 최대 짝 수는 이름의 갯수보다 클 수 없습니다");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            run();
        }
    }

    // 랜덤 짝꿍 추첨하는 함수 입니다.
    public List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {
        Collections.shuffle(memberList);
        List<List<String>> newMemberList = new ArrayList<>();
        List<String> newList = new ArrayList<>();
        for (String s : memberList) {
            newList.add(s);
            if (newList.size() == maximumGroupSize || memberList.indexOf(s) == memberList.size()-1) {
                newMemberList.add(new ArrayList<>(newList));
                newList.clear();
            }
        }
        return newMemberList;
    }

    // 결과를 프린트 하는 함수입니다.
    public void printResult(List<List<String>> result) {
        System.out.println("오늘의 짝 추천 결과입니다.");
        for (List<String> line : result) {
            StringBuilder sb = new StringBuilder();
            sb.append("[ ");
            for (String s : line) {
                sb.append(s).append(" | ");
            }
            sb.replace(sb.length()-2,sb.length(),"]");
            System.out.println(sb);
        }
        System.out.println("추천을 완료했습니다.");
    }

    public static Boolean isContinue() {
        System.out.print("다시 구성하시겠습니까? (y or n): ");
        try {
            Scanner sc = new Scanner(System.in);
            String continueFlag = sc.nextLine();
            if (continueFlag.equals("n")) {
                System.out.println("자리를 이동해 서로에게 인사해주세요.");
                return false;
            } else if (continueFlag.equals("y")) {
                System.out.println("--------------------------------");
                return true;
            } else {
                throw new IllegalArgumentException("[ERROR] y 혹은 n만 입력해주세요.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return isContinue();
        }
    }

    public static void main(String[] args) {
        LeetsMateApplication app = new LeetsMateApplication();
        app.run();
    }
}