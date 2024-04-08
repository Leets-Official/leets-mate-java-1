package leets.leets_mate.domain;

import java.util.Arrays;

public enum RetryOpinion {

    CONTINUE("y", true),
    STOP("n", false);

    private final String input;
    private final boolean hasRetry;

    RetryOpinion(String input, boolean hasRetry) {
        this.input = input;
        this.hasRetry = hasRetry;
    }

    public static RetryOpinion match(String input) {
        return Arrays.stream(values())
                .filter(retryOpinion -> retryOpinion.input.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 값입니다."));
    }

    public boolean isContinue() {
        return this.hasRetry;
    }
}
