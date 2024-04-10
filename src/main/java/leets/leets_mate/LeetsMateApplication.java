package leets.leets_mate;

import java.util.*;
import java.io.*;

public class LeetsMateApplication {

    // 동작 함수입니다.
    public void run() {
        Scanner sc = new Scanner(System.in);
        String members = sc.nextLine();
        int ret = 1;

        // 영어 없는지 확인
        checkHasNoEnglish(members);

        // 리스트로 멤버 분리
        List<String> memArray = parseMembers(members);

        // 멤버 명수 확인
        int memberCount = memberNumber(memArray);
        // 이름 개수보다 짝 수가 더 많으면 예외발생
        int maximumGroupSize = Integer.parseInt(sc.nextLine());
        checkDataValidity(memberCount, maximumGroupSize);


        // 다시 생성을 진행할지 묻는 함수

        while (ret == 1){
            // 랜덤 짝궁 추천
            printResult(generateRandomGroups(memArray, maximumGroupSize));

            System.out.print("다시 구성하시겠습니까? (y or n): ");
            String re = sc.nextLine();
            System.out.println("--------------------------------");
            ret = reGeneration(re);

            if(ret != 1) break;
        }

        sc.close();
    }

    // 문자열로된 멤버들을 리스트로 분리하는 함수입니다.
    public List<String> parseMembers(String members) {
        // 저장할 리스트
        List<String> memArray = new ArrayList<String>();

        // ,를 기준으로 문자열 분리
        StringTokenizer st = new StringTokenizer(members, ",");

        // 문자열 리스트에 저장
        while(st.hasMoreTokens())
            memArray.add(st.nextToken());

        return memArray;
    }

    // 총 멤버수를 반환합니다.
    public int memberNumber(List<String> members) {
        return members.size();
    }

    // 멤버 문자열에 영어가 있는지 검사합니다. 영어가 있다면 예외 출력
    public void checkHasNoEnglish(String members) {
        if (!members.matches("[가-힣,]+")) {
            throw new IllegalArgumentException("[ERROR] 이름은 한글로 입력해야 합니다");
        }
    }

    // 멤버수와 최대 짝수 데이터가 유효한지 검사하는 함수입니다. 유효하지 않다면 예외 출력
    public void checkDataValidity(int memberCount, int maximumGroupSize) {
        if (memberCount < maximumGroupSize) {
            throw new IllegalArgumentException("[ERROR] 최대 짝 수는 이름의 갯수보다 클 수 없습니다");
        }
    }

    // 랜덤 짝꿍 추첨하는 함수 입니다.
    public List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {
        Collections.shuffle(memberList);
        List<List<String>> groups = new ArrayList<>();
        for (int i = 0; i < memberList.size(); i += maximumGroupSize) {
            groups.add(new ArrayList<>(memberList.subList(i, Math.min(i + maximumGroupSize, memberList.size()))));
        }
        return groups;
    }

    // 결과를 프린트 하는 함수입니다.
    public void printResult(List<List<String>> result) {
        System.out.println("오늘의 짝 추천 결과입니다.");
        for (List<String> group : result) {
            System.out.print("[ ");
            for (int i = 0; i < group.size(); i++) {
                System.out.print(group.get(i));
                if (i < group.size() - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println(" ]");
        }
        System.out.println("추천을 완료했습니다.");
    }

    // 다시 추첨을 진행하는 함수입니다.
    public int reGeneration(String re) {
        if(Objects.equals(re, "n")) {
            System.out.println("자리를 이동해 서로에게 인사해주세요.");
            return 0;
        } else if (Objects.equals(re, "y")) {
            return 1;
        } else {
            throw new IllegalArgumentException("[ERROR] y또는 n을 입력해야 합니다");
        }
    }

    public static void main(String[] args) {
        LeetsMateApplication app = new LeetsMateApplication();
        app.run();
    }
}