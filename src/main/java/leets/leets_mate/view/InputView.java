package leets.leets_mate.view;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    public static final String COMMA = ",";
    public static final Pattern NAME_FORMAT = Pattern.compile("^[ㄱ-ㅎ|가-힣]+$");
    public static final Pattern RETRY_FORMAT = Pattern.compile("^[y|n]$");
    public static final String ERROR_FORMAT = "[ERROR] %s";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        System.out.println();
        System.out.println("멤버의 이름을 입력해 주세요. (, 로 구분)");

        String input = scanner.nextLine();
        List<String> rawNames = List.of(input.split(COMMA, -1));
        try {
            rawNames.forEach(this::validateInput);
            return rawNames;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readNames();
        }
    }

    private void validateInput(String rawName) {
        if (!NAME_FORMAT.matcher(rawName).matches()) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT, "한글만 입력해 주세요."));
        }
    }

    public int readPairCount() {
        System.out.println();
        System.out.println("최대 짝 수를 입력해 주세요.");

        String input = scanner.nextLine();
        try {
            return parseToInt(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readPairCount();
        }
    }

    private int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT, "숫자를 입력해 주세요."));
        }
    }

    public String readRetry() {
        System.out.print("다시 구성하시겠습니까? (y or n): ");

        String input = scanner.nextLine();
        try {
            validate(input);
            return input;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readRetry();
        }
    }

    private void validate(String input) {
        if (!RETRY_FORMAT.matcher(input).matches()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }
}
