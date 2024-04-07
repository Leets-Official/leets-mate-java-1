package leets.leets_mate.view;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    public static final String COMMA = ",";
    public static final Pattern FORMAT = Pattern.compile("^[ㄱ-힇]*$");
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
        if (!FORMAT.matcher(rawName).matches()) {
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
}
