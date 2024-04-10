package leets.leets_mate;

import leets.leets_mate.error.ErrorCode;
import leets.leets_mate.error.ErrorResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LeetsMateApplication {
    public static final String REGEXP_KR = "^[ㄱ-ㅎ가-힣, ]*$";

    // 동작 함수입니다.
    public void run() throws IOException {

        System.out.println("[Leets 오늘의 짝에게]를 시작합니다.\n");
        // 참석자들의 이름을 입력받는다. 한 줄로 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        // 입력을 검사합니다.
        do {
            System.out.println("참석자들의 이름을 입력해 주세요. (,로 구분)");
            input = br.readLine();
        } while (checkHasNoEnglish(input));

        // 멤버를 리스트로 분리
        List<String> members = parseMembers(input);
        int maximumGroupSize;

        // 최대 짝(그룹) 인원 수를 입력받는다
        do {
            System.out.println("최대 짝 수를 입력해 주세요.");
            maximumGroupSize = Integer.parseInt(br.readLine());
        } while (checkDataValidity(members.size(), maximumGroupSize));

        // 오늘의 짝 추천 결과를 리턴한다.
        printResult(members, maximumGroupSize);
    }

    // 문자열로된 멤버들을 리스트로 분리하는 함수입니다.
    public List<String> parseMembers(String members) {
        return new ArrayList<>(List.of(members.split(",")));
    }

    // 총 멤버수를 반환합니다.
    public int memberNumber(List<String> members) {
        return members.size();
    }

    // 멤버 문자열에 영어가 있는지 검사합니다. 영어가 있다면 예외 출력
    public boolean checkHasNoEnglish(String member) {
        /**
         * -> 입력 조건이 한글 및 "," 로만 이루어져야 합니다.
         * 즉, 영어가 있을 때 에러를 반환하는 로직보다
         * 한글과 "," 을 제외한 어떤 문자가 들어오더라도 에러를 반환하는 것이
         * 문제 입력 조건에 좀 더 적합하다고 판단하였습니다.
         */
        if (!Pattern.matches(REGEXP_KR, member)) {
            System.out.println(ErrorResponse.of(ErrorCode.INPUT_FORMAT_ERROR).getMessage());
            return true;
        }
        return false;
    }

    // 멤버수와 최대 짝수 데이터가 유효한지 검사하는 함수입니다. 유효하지 않다면 예외 출력
    public boolean checkDataValidity(int memberSize, int maximumGroupSize) {
        boolean flag = (memberSize < maximumGroupSize);
        if (flag) System.out.println(ErrorResponse.of(ErrorCode.GROUP_SIZE_ERROR).getMessage());
        return flag;
    }

    // 랜덤 짝꿍 추첨하는 함수 입니다.
    public static List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {
        // 입력 리스트를 랜덤으로 섞음
        Collections.shuffle(memberList);

        List<List<String>> randomGroups = new ArrayList<>();
        int startIndex = 0;

        while (startIndex < memberList.size()) {
            int groupSize = Math.min(memberList.size() - startIndex, maximumGroupSize);
            randomGroups.add(new ArrayList<>(memberList.subList(startIndex, startIndex + groupSize)));
            startIndex += groupSize;
        }
        return randomGroups;
    }

    // 결과를 프린트 하는 함수입니다.
    public void printResult(List<String> members, int maximumGroupSize) {

        List<List<String>> result = generateRandomGroups(members, maximumGroupSize);

        System.out.println("오늘의 짝 추천 결과입니다.");
        for (List<String> innerList : result) {
            String resultString = innerList.stream()
                    .collect(Collectors.joining(" | ", "[ ", " ]"));
            System.out.println(resultString);
        }
        System.out.println("\n추천을 완료했습니다.");

        boolean flag = true;
        while (flag) {
            System.out.print("다시 구성하시겠습니까? (y or n): ");
            Scanner sc = new Scanner(System.in);
            String c = sc.nextLine();
            switch (c) {
                case "y" -> {
                    flag = false;
                    System.out.println("--------------------------------");
                    printResult(members, maximumGroupSize);
                }
                case "n" -> {
                    flag = false;
                    System.out.println("자리를 이동해 서로에게 인사해주세요.");
                }
                default -> System.out.println(ErrorResponse.of(ErrorCode.COMMAND_ERROR).getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        LeetsMateApplication app = new LeetsMateApplication();
        app.run();
    }
}