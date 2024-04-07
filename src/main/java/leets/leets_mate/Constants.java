package leets.leets_mate;

public enum Constants {
    NEWLINE("\n"),
    INTRO("Leets [오늘의 짝에게]를 시작합니다!"),
    INPUT("동아리원의 이름을 ','로 구분하여 한글로 입력해주세요!"),
    MATE("최대 짝(그룹 수)를 입력해주세요! 단, 최대 짝은 동아리원의 수보다 작거나 같습니다."),
    RESULT("오늘의 짝 추천 결과입니다."),
    CONTINUE("추천을 완료했습니다.\n" + "다시 구성하시겠습니까? (y or n"),
    HELLO("자리를 이동해 서로에게 인사해주세요!"),
    ERROR_INPUT("[ERROR] 잘못된 입력입니다. 다시 입력해주세요."),
    ERROR_MEMBER_INPUT("[ERROR] 이름은 한글로 입력해야 합니다"),
    ERROR_MAXSIZE("[ERROR] 최대 짝 수는 이름의 갯수보다 클 수 없습니다");

    private final String message;

    public String getMessage() {
        return message;
    }

    private Constants(String message) {
        this.message = message;
    }
}
