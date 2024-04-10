package leets.leets_mate.domain;

public class PairCountValidator {

    private static final int MIN_COUNT = 2;
    private static final String ERROR_FORMAT = "[ERROR] 최대 짝 수는 %d 이상, 이름 수 이하여야 합니다.";

    public void validate(int maxCount, int namesCount) {
        if (maxCount < MIN_COUNT || maxCount > namesCount) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT, MIN_COUNT));
        }
    }
}
