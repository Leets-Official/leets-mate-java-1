package leets.leets_mate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class LeetsMateApplication {

    // 동작 함수입니다.
    public void run() throws Exception {

        System.out.println("[Leets 오늘의 짝에게]를 시작합니다.\n");

        //멤버 문자열 입력 받기 및 파싱
        System.out.println("멤버의 이름을 입력해 주세요. (, 로 구분)");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<String> memberList = parseMembers(input);

        //최대 짝 수 입력 받기 및 유효성 검사
        System.out.println("\n최대 짝 수를 입력해 주세요.");
        int maximumGroupSize = 0;
        try{
            maximumGroupSize = Integer.parseInt(sc.nextLine());
        }
        catch (Exception e){
            throw new Exception("[ERROR] 최대 짝 수는 숫자여야합니다");
        }
        checkDataValidity(memberNumber(memberList), maximumGroupSize);

        char re = 'y';
        while(re == 'y'){
            //랜덤 짝 생성 및 출력
            System.out.println("\n오늘의 짝 추천 결과입니다.");
            List<List<String>> result = generateRandomGroups(memberList, maximumGroupSize);
            printResult(result);
            System.out.println("\n추천을 완료했습니다.");

            //재구성
            System.out.print("다시 구성하시겠습니까? (y or n): ");
            re = sc.nextLine().charAt(0);
            if (re != 'y' && re != 'n') {
                throw new Exception("[ERROR]'y' 또는 'n'을 입력해주세요");
            }
            if(re == 'y'){
                System.out.print("--------------------------------");
            }
        }
        System.out.println("자리를 이동해 서로에게 인사해주세요.");

    }

    // 문자열로된 멤버들을 리스트로 분리하는 함수입니다.
    public List<String> parseMembers(String members) throws Exception {
        checkHasNoEnglish(members);
        List<String> memberList = Arrays.asList(members.split(","));
        return memberList;
    }

    // 총 멤버수를 반환합니다.
    public int memberNumber(List<String> members) {
        return members.size();
    }

    // 멤버 문자열에 영어가 있는지 검사합니다. 영어가 있다면 예외 출력
    public void checkHasNoEnglish(String members) throws Exception {
        // 멤버 문자열에 한글, 컴마(,), 공백만 허용
        if(!Pattern.matches("^[ㄱ-ㅎ가-힣,\\s]*$", members)){
            //예외 발생
            throw new Exception("[ERROR] 이름은 한글로 입력해야 합니다");
        }
    }

    // 멤버수와 최대 짝수 데이터가 유효한지 검사하는 함수입니다. 유효하지 않다면 예외 출력
    public void checkDataValidity(int memberCount, int maximumGroupSize) throws Exception {
        if(memberCount < maximumGroupSize){
            throw new Exception("[ERROR] 최대 짝 수는 이름의 갯수보다 클 수 없습니다\n");
        }
        if(maximumGroupSize <= 0){
            throw new Exception("[ERROR] 최대 짝 수는 1 이상이어야 합니다\n");
        }
    }

    // 랜덤 짝꿍 추첨하는 함수 입니다.
    public List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {

        Collections.shuffle(memberList);

        List<String> mate = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();

        int memberCount = memberNumber(memberList);

        for(int i = 0; i<memberCount; i++){
            mate.add(memberList.get(i));

            if(mate.size() == maximumGroupSize
                || i == memberCount - 1){
                result.add(new ArrayList<>(mate));
                mate = new ArrayList<>();
            }
        }

        return result;
    }

    // 결과를 프린트 하는 함수입니다.
    public void printResult(List<List<String>> result) {
        for (List<String> mate : result) {
            System.out.print("[ ");
            Iterator<String> iterator = mate.iterator();

            while (iterator.hasNext()) {
                String name = iterator.next();
                System.out.print(name);

                if (iterator.hasNext()) {
                    System.out.print(" | ");
                }
            }

            System.out.print(" ]\n");
        }
    }

    public static void main(String[] args) throws Exception {
        LeetsMateApplication app = new LeetsMateApplication();
        app.run();
    }
}