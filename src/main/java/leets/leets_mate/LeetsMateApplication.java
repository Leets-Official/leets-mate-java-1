package leets.leets_mate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static leets.leets_mate.Constants.*;

public class LeetsMateApplication {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<String> parsedMembers;
    static int groupSize;

    // 동작 함수입니다.
    public void run() throws IOException {
        printIntro();

        String members = br.readLine();
        try {
            checkHasNoEnglish(members);
        } catch (Exception e) {
            System.out.println(ERROR_MEMBER_INPUT.getMessage());
            run();
        }

        parsedMembers = parseMembers(members);
        groupSize = getGroupSize();
        List<List<String>> finalMembers = generateRandomGroups(parsedMembers, groupSize);

        printResult(finalMembers);
        System.out.println(HELLO.getMessage());
    }

    private static void printIntro() {
        System.out.println(INTRO.getMessage());
        System.out.print(NEWLINE.getMessage());
        System.out.println(INPUT.getMessage());
    }

    private int getGroupSize() throws IOException {
        System.out.print(NEWLINE.getMessage());
        System.out.println(MATE.getMessage());

        int size = checkGroupSize(); //짝 수

        try {
            checkDataValidity(memberNumber(parsedMembers), size);
        } catch (Exception e) {
            System.out.println(ERROR_MAXSIZE.getMessage());
            getGroupSize();
        }
        return size;
    }

    private int checkGroupSize() throws IOException {
        int size = 0;

        try {
            size = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.printf(ERROR_INPUT.getMessage());
            checkGroupSize();
        }

        return size;
    }

    // 문자열로된 멤버들을 리스트로 분리하는 함수입니다.
    public List<String> parseMembers(String members) {
        String[] arr = members.split(",");
        return Arrays.stream(arr).collect(Collectors.toList());
    }

    // 총 멤버수를 반환합니다.
    public int memberNumber(List<String> members) {
        return members.size();
    }

    // 멤버 문자열에 영어가 있는지 검사합니다. 영어가 있다면 예외 출력
    public void checkHasNoEnglish(String members) {
        String regex = ".*[a-zA-Z].*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(members);

        if (matcher.matches()) {
            throw new IllegalArgumentException();
        }
    }

    // 멤버수와 최대 짝수 데이터가 유효한지 검사하는 함수입니다. 유효하지 않다면 예외 출력
    public void checkDataValidity(int memberCount, int maximumGroupSize) {
        if (memberCount < maximumGroupSize) {
            throw new IllegalArgumentException();
        }
    }

    // 랜덤 짝꿍 추첨하는 함수 입니다.
    public List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {
        Collections.shuffle(memberList);

        List<List<String>> groups = new ArrayList<>();
        for (int i = 0; i < memberList.size(); i += maximumGroupSize) {
            int end = Math.min(i + maximumGroupSize, memberList.size());
            groups.add(new ArrayList<>(memberList.subList(i, end)));
        }

        return groups;
    }

    // 결과를 프린트 하는 함수입니다.
    public void printResult(List<List<String>> result) {
        StringBuilder sb = new StringBuilder();
        System.out.print(NEWLINE.getMessage());
        System.out.println(RESULT.getMessage());

        for (List<String> group : result) {
            sb.append("[ ");
            for (int i = 0; i < group.size(); i++) {
                sb.append(group.get(i));
                if (i != group.size() - 1) {
                    sb.append(" | ");
                }
            }
            sb.append(" ]").append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        LeetsMateApplication app = new LeetsMateApplication();
        app.run();
    }
}