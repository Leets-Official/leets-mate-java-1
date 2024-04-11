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
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private List<String> parsedMembers;
    private int groupSize;

    public void run() throws IOException {
        printIntro();
        String members = readInput();
        try {
            checkHasNoEnglish(members);
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MEMBER_INPUT.getMessage());
            run();
            return;
        }

        parsedMembers = parseMembers(members);
        groupSize = getGroupSize();

        try {
            getResult();
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_INPUT.getMessage());
            getResult();
        }
        System.out.println(HELLO.getMessage());
    }

    private void getResult() throws IOException {
        System.out.print(NEWLINE.getMessage());
        System.out.println(RESULT.getMessage());

        List<List<String>> finalMembers = generateRandomGroups(parsedMembers, groupSize);

        printResult(finalMembers);
        System.out.print(CONTINUE.getMessage());
        String input = br.readLine();

        if (input.equals("y")) {
            System.out.println("--------------------------------");
            getResult();
        } else if (!input.equals("n")) {
            throw new IllegalArgumentException();
        }
    }

    private void printIntro() {
        System.out.println(INTRO.getMessage());
        System.out.print(NEWLINE.getMessage());
        System.out.println(INPUT.getMessage());
    }

    private String readInput() throws IOException {
        return br.readLine();
    }

    private int getGroupSize() throws IOException {
        System.out.print(NEWLINE.getMessage());
        System.out.println(MATE.getMessage());

        int size = checkGroupSize(); // Must be even

        try {
            checkDataValidity(memberNumber(parsedMembers), size);
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MAXSIZE.getMessage());
            size = getGroupSize();
        }
        return size;
    }

    private int checkGroupSize() throws IOException {
        int size = 0;

        try {
            size = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println(ERROR_INPUT.getMessage());
            checkGroupSize();
        }

        return size;
    }

    public List<String> parseMembers(String members) {
        return Arrays.stream(members.split(",")).collect(Collectors.toList());
    }

    public int memberNumber(List<String> members) {
        return members.size();
    }

    public void checkHasNoEnglish(String members) {
        String regex = "^[ㄱ-ㅎ|가-힣]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(members);

        if (!matcher.matches()) {
            throw new IllegalArgumentException();
        }
    }

    public void checkDataValidity(int memberCount, int maximumGroupSize) {
        if (memberCount < maximumGroupSize) {
            throw new IllegalArgumentException();
        }
    }

    public List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {
        Collections.shuffle(memberList);

        List<List<String>> groups = new ArrayList<>();
        for (int i = 0; i < memberList.size(); i += maximumGroupSize) {
            int end = Math.min(i + maximumGroupSize, memberList.size());
            groups.add(new ArrayList<>(memberList.subList(i, end)));
        }

        return groups;
    }

    public void printResult(List<List<String>> result) {
        StringBuilder sb = new StringBuilder();

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
