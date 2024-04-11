package leets.leets_mate;

import leets.leets_mate.validation.exception.InvalidInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class LeetsMateApplication {

    private static final int MAX_ERROR_COUNT = 5;   // 최대 입력 횟수
    private static final int INITIAL_ERROR_COUNT = 0;   // 입력 횟수 세기 시작
    private static final Pattern IS_KOREAN = Pattern.compile("^[ㄱ-ㅎㅏ-ㅣ가-힣]*$");     // regex 미리 컴파일
    private static final Pattern IS_INTEGER = Pattern.compile("\\d+");
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 테스트 코드 실행을 위한 BufferedReader Setter
    public void setBufferedReader(BufferedReader br) {
        this.br = br;
    }

    public static void main(String[] args) {
        LeetsMateApplication app = new LeetsMateApplication();
        app.run();
    }

    // 동작 함수입니다.
    public void run() {
        /** 설계
         *  1. 이름 입력 받기
         *      a. 입력 받기 (read)
         *      b. String -> List (parseMember)
         *      b. regex (checkHasNoEnglish)
         *  2. 최대 짝(그룹) 인원 수 입력 받기
         *      a. 총 멤버 수 계산 (memberNumber)
         *      b. 입력 받기 (read)
         *      c. int형으로 안전하게 변환 (convertToInteger)
         *      d. '멤버 수 > 팀당 인원 수' 검사 (checkDataValidity)
         *      e. 최대 짝(그룹) 인원 수 입력 받기 (getSize)
         *  3. 짝 랜덤 추첨 -> 출력
         *      a. 짝 추첨 실행 (executeDraw)
         *      b, 짝 생성 (generateRandomGroups)
         *      c. 결과 출력 (printResult)
         *      d. 추첨을 멈출 것인지 결정 (isBreak)
         *
         *   예외) 사용자가 5번 이상 올바르지 않은 값을 입력할 시 프로그램 종료
         */

        System.out.println("""
                [Leets 오늘의 짝에게]를 시작합니다.

                멤버의 이름을 입력해주세요. (, 로 구분)""");

        // 1. 이름 입력 받기
        List<String> memberList = parseMembers(INITIAL_ERROR_COUNT);

        // 2. 인원 수 입력 받기
        System.out.println("최대 짝 수를 입력해주세요.");
        int size = getSize(memberNumber(memberList), INITIAL_ERROR_COUNT);

        // 3. 짝 추첨 -> 출력
        executeDraw(memberList, size);
    }

    // 문자열로된 멤버들을 리스트로 분리하는 함수입니다.
    public List<String> parseMembers(int errorCnt) {
        try {
            return Arrays.stream(read(errorCnt).split(","))
                    .peek(this::checkHasNoEnglish)  // validation
                    .collect(Collectors.toList());
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return parseMembers(++errorCnt);
        }

    }

    // 총 멤버수를 반환합니다.
    public int memberNumber(List<String> members) {
        return members.size();
    }

    // 랜덤 짝꿍 추첨하는 함수 입니다.
    public List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {
        Collections.shuffle(memberList);    // 리스트 셔플

        int groupCnt = (int) Math.ceil((double) memberList.size() / maximumGroupSize);
        return IntStream.range(0, groupCnt) // 0 이상 그룹 개수 미만 범위 루프
                .mapToObj(i -> memberList.subList(i * maximumGroupSize,
                        Math.min((i + 1) * maximumGroupSize, memberList.size()) // 마지막 팀에 인원이 가득 차지 않았을 때 범위를 넘어가지 않기 위해 min 연산
                ))
                .collect(Collectors.toList());
    }

    // 결과를 프린트 하는 함수입니다.
    public void printResult(List<List<String>> result) {
        StringJoiner groupJoiner = new StringJoiner("\n");
        for (List<String> group : result) {
            StringJoiner memberJoiner = new StringJoiner(" | ", "[ ", " ]");
            for (String member : group) {
                memberJoiner.add(member);
            }
            groupJoiner.add(memberJoiner.toString());
        }
        System.out.println(groupJoiner);
    }

    // 원하는 결과가 나올 때까지 추첨을 진행하는 함수입니다.
    public void executeDraw(List<String> memberList, int size) throws InvalidInputException {
        System.out.println("\n오늘의 짝 추천 결과입니다.");

        List<List<String>> result = generateRandomGroups(memberList, size); // 짝 추첨
        printResult(result);    // 출력
        System.out.println("추천을 완료했습니다.");

        if (!isBreak(INITIAL_ERROR_COUNT))
            executeDraw(memberList, size);
    }

    // 추첨을 멈출 것인지 결정하는 함수입니다.
    public boolean isBreak(int errorCnt) {
        try {
            System.out.print("다시 구성하시겠습니까? (y or n): ");
            String input = read(errorCnt);

            if (input.equals("y")) {    // 재구성
                System.out.println("--------------------------------");
                return false;
            }

            if (input.equals("n")) {    // 고정
                System.out.println("자리를 이동해 서로에게 인사해주세요.");
                return true;
            }

            // 그 외의 잘못된 응답 처리
            throw new InvalidInputException("[ERROR] 응답은 y 혹은 n으로 입력해야 합니다.");
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return isBreak(++errorCnt);
        }
    }

    // 사용자의 모든 입력을 처리하는 함수입니다.
    public String read(int errorCnt) {
        try {
            if (errorCnt >= MAX_ERROR_COUNT)
                throw new InvalidInputException("[ERROR] 5회 이상 잘못 입력하셨습니다.\n프로그램을 종료합니다.");


            return Optional.of(br.readLine())
                    .filter(input -> !input.trim().isEmpty())
                    .orElseThrow(() -> new InvalidInputException("[ERROR] 값을 입력해주세요.")); // validation
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());

            if (errorCnt >= MAX_ERROR_COUNT)
                System.exit(0);

            return read(++errorCnt);
        } catch (IOException e) {
            throw new RuntimeException("[ERROR] 입력을 읽는 중 오류가 발생했습니다.", e);
        }
    }

    // 그룹의 크기를 가져오는 함수입니다.
    public int getSize(int memberCnt, int errorCnt) {
        try {
            return checkDataValidity(memberCnt, convertToInteger(read(errorCnt)));
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return getSize(memberCnt, ++errorCnt);
        }
    }

    // validation
    public void checkHasNoEnglish(String members) throws InvalidInputException {
        if (!IS_KOREAN.matcher(members).matches())
            throw new InvalidInputException("[ERROR] 이름은 공백없이 한글로 입력해야 합니다. 다시 입력해주세요.");
    }

    public int checkDataValidity(int memberCount, int maximumGroupSize) throws InvalidInputException {
        if (memberCount < maximumGroupSize)
            throw new InvalidInputException("[ERROR] 최대 짝 수는 이름의 갯수보다 클 수 없습니다. 다시 입력해주세요.");
        if (maximumGroupSize < 1)
            throw new InvalidInputException("[ERROR] 최대 짝 수는 1보다 작을 수 없습니다. 다시 입력해주세요.");
        return maximumGroupSize;
    }

    public int convertToInteger(String input) throws InvalidInputException {
        if (!IS_INTEGER.matcher(input).matches())
            throw new InvalidInputException("[ERROR] 알맞은 짝의 크기를 입력해주세요.");
        return Integer.parseInt(input);
    }
}