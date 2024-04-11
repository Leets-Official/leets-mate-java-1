package leets.leets_mate.view.exception;

public enum IllegalArgumentExceptionType implements ExceptionType<IllegalArgumentException> {

    INVALID_NAMING("[ERROR] 이름은 한글로 입력해야 합니다"),
    GROUP_SIZE_EXCEEDS_MEMBER_COUNT("[ERROR] 최대 짝 수는 이름의 갯수보다 클 수 없습니다."),
    INVALID_MAX_GROUP("[ERROR] 최대 짝수는 숫자로 입력해야 합니다"),
    IVALID_RETRY_INPUT("[ERROR] y와 n만 입력이 가능합니다.");

    private final String message;

    IllegalArgumentExceptionType(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public IllegalArgumentException getException() {
        return new IllegalArgumentException(getMessage());
    }
}
