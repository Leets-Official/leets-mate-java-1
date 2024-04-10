package leets.leets_mate.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INPUT_FORMAT_ERROR(400, "INPUT_FORMAT_ERROR","참가자 입력은 한글과 ',' 으로만 이루어져야 합니다"),
    COMMAND_ERROR(400, "COMMAND_ERROR","y또는 n으로 입력해주세요"),
    GROUP_SIZE_ERROR(400, "GROUP_SIZE_ERROR", "최대 인원수보다 작거나 같은 숫자를 입력해주세요");

    private final int httpStatus;
    private final String code;
    private final String message;
}
