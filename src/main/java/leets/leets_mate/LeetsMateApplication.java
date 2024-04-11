package leets.leets_mate;

import com.google.common.collect.Lists;

import java.util.*;

public class LeetsMateApplication {

    //반복된 입력작업을 위해 전역변수로 선언
    Scanner scan = new Scanner(System.in);
    String members;

    // 동작 함수입니다.
    public void run() {
        //입력부
        System.out.println("[Leets 오늘의 짝에게]를 시작합니다.");
        System.out.println("멤버의 이름을 입력해 주세요. ',' 로 구분");

        //영어 입력시 예외처리
        members = inputMembers(members);

        //문자열에서 List로 변환
        List<String> memberList = parseMembers(members);

        //최대 짝 수 입력. 예외처리
        int maxGroup = checkMaxGroup(memberList);

        //버퍼 속 개행문자 소비
        scan.nextLine();

        //초기 결과 출력
        printResult(generateRandomGroups(memberList,maxGroup));

        //다시추첨기능, y나n을 입력하지 않으면 예외처리
        reDraw(memberList, maxGroup);
    }

    //다시 추첨 기능
    private void reDraw(List<String> memberList, int maxGroup) {
        String reDrawOpinion;
        while(true){
            System.out.println("다시 추첨하겠습니까?");
            reDrawOpinion = scan.nextLine();
            reDrawOpinion = getOpinion(reDrawOpinion);
            if(reDrawOpinion.equals("n")){
                break;
            }
            printResult(generateRandomGroups(memberList, maxGroup));
        }
    }

    //다시 추첨 입력 예외처리
    private String getOpinion(String index) {
        while(true){
            try{
                checkYesOrNo(index);
                break;
            }catch (IllegalArgumentException e){
                System.out.println("y나 n을 입력해주세요");
                index = scan.nextLine();
            }
        }
        return index;
    }

    //최대 짝 수 크기 예외 처리
    private int checkMaxGroup(List<String> memberList) {
        System.out.println("\n최대 짝 수를 입력해주세요");
        int maxGroup;
        while (true) {
            try {
                maxGroup = scan.nextInt();
                checkDataValidity(memberNumber(memberList), maxGroup);
                break;
            } catch (InputMismatchException e) {
                System.out.println("최대 짝 수를 다시 입력해주세요");
                scan.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("최대 짝 수를 다시 입력해주세요");
                scan.nextLine();
            }
        }
        return maxGroup;
    }


    //멤버 입력 예외처리
    private String inputMembers(String members) {
        //초기 멤버 입력
        members = scan.nextLine();
        while(true) {
            try {
                checkHasNoEnglish(members);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("멤버의 이름을 한글로 다시 입력해 주세요. ',' 로 구분");
                members = scan.nextLine();
            }
        }
        return members;
    }


    // 문자열로된 멤버들을 리스트로 분리하는 함수입니다.
    public List<String> parseMembers(String members) {
        String[] parsedMember = members.split(",");
        return Arrays.asList(parsedMember);
    }

    // 총 멤버수를 반환합니다.
    public int memberNumber(List<String> members) {
        return members.size();
    }

    // 멤버 문자열에 영어가 있는지 검사합니다. 영어가 있다면 예외 출력
    public void checkHasNoEnglish(String members) {
        for (Character c : members.toCharArray()) {
            if(!((c >= 'ㄱ' && c <= '힣')||c==',')){
                throw new IllegalArgumentException("[ERROR] 이름은 한글로 입력해야 합니다.");
            }
        }
    }

    // 멤버수와 최대 짝수 데이터가 유효한지 검사하는 함수입니다. 유효하지 않다면 예외 출력
    public void checkDataValidity(int memberCount, int maximumGroupSize) {
        if(memberCount<maximumGroupSize){
            throw new IllegalArgumentException("[ERROR] 최대 짝 수는 이름의 갯수보다 클 수 없습니다.");
        }
    }

    //다시 추첨 기능에서 y,n을 입력하는지 검사하는 함수입니다. 유효하지 않다면 예외 출력
    public void checkYesOrNo(String index){
        if(!((index.equals("y"))|(index.equals("n")))){
            throw new IllegalArgumentException("[ERROR] 입력에는 'y','n'만 가능합니다.");
        }
    }

    // 랜덤 짝꿍 추첨하는 함수 입니다.
    public List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {
        Collections.shuffle(memberList);
        return Lists.partition(memberList, maximumGroupSize);
    }

    // 결과를 프린트 하는 함수입니다.
    public void printResult(List<List<String>> result) {
        System.out.println("오늘의 짝 추첨 결과");
        for (List<String> strings : result) {
            System.out.println(strings);
        }
        System.out.println("\n추첨을 완료하였습니다.\n");
    }

    public static void main(String[] args) {
        LeetsMateApplication app = new LeetsMateApplication();
        app.run();
    }
}