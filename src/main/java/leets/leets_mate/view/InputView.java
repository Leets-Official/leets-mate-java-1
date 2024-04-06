package leets.leets_mate.view;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    public static final String COMMA = ",";
    public static final Pattern FORMAT = Pattern.compile("^[ㄱ-힇]*$");

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        String input = scanner.nextLine();
        List<String> rawNames = List.of(input.split(COMMA, -1));
        for (String rawName : rawNames) {
            validateInput(rawName);
        }
        return rawNames;
    }

    private void validateInput(String rawName) {
        if (!FORMAT.matcher(rawName).matches()) {
            throw new IllegalArgumentException("한글만 입력해 주세요");
        }
    }
}
