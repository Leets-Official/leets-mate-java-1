package leets.leets_mate;

import java.util.*;

public class LeetsMateApplication {

    // 동작 함수입니다.
    public void run() {
        Scanner scanner=new Scanner(System.in);
        try{
            System.out.println("누구누구있어?");
            String leetsMember=scanner.nextLine();
            checkHasNoEnglish(leetsMember);

            List<String> membersList=parseMembers((leetsMember));

            System.out.println("몇명씩할래?");
            int groupNum=scanner.nextInt();
            checkDataValidity(membersList.size(),groupNum);

            List<List<String>> leetsGroup=generateRandomGroups(membersList,groupNum);

            System.out.println("결과야.");
            printResult(leetsGroup);

            while(true){
                System.out.println("마음에 안들어?(y/n)");
                String answer=scanner.next();
                if(answer.equalsIgnoreCase("y")){
                    leetsGroup=generateRandomGroups(membersList,groupNum);
                    printResult(leetsGroup);
                }
                else if(answer.equalsIgnoreCase("n")){
                    break;
                }
            }
        }catch(IllegalArgumentException e){
            System.out.println("[ERROR]"+ e.getMessage());
            run();
        }finally{
            scanner.close();
        }
    }

    // 문자열로된 멤버들을 리스트로 분리하는 함수입니다.
    public List<String> parseMembers(String members) {
        List<String> memberList=new ArrayList<>();
        String[] divide=members.split(",");
        for(String member:divide){
            memberList.add(member);
        }
        return memberList;
    }

    // 총 멤버수를 반환합니다.
    public int memberNumber(List<String> members) {
        return members.size();
    }

    // 멤버 문자열에 영어가 있는지 검사합니다. 영어가 있다면 예외 출력
    public void checkHasNoEnglish(String members) {
        if(members.matches(".*[a-zA-Z].*")){
            throw new IllegalArgumentException("영어가 들어가면 안된다.");
        }
    }

    // 멤버수와 최대 짝수 데이터가 유효한지 검사하는 함수입니다. 유효하지 않다면 예외 출력
    public void checkDataValidity(int memberCount, int maximumGroupSize) {
        if(memberCount<=0||maximumGroupSize<=0||maximumGroupSize>memberCount){
            throw new IllegalArgumentException(("멤버수와 최대짝수 데이터가 유효하지 않습니다."));
        }
    }

    // 랜덤 짝꿍 추첨하는 함수 입니다.
    public List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {
        List<String> afterMembers = new ArrayList<>(memberList);
        Collections.shuffle(afterMembers);
        List<List<String>> groups = new ArrayList<>();
        for(int i=0;i<afterMembers.size();i+=maximumGroupSize){
            groups.add(afterMembers.subList(i,Math.min(i+maximumGroupSize,afterMembers.size())));
        }
        return groups;
    }

    // 결과를 프린트 하는 함수입니다.
    public void printResult(List<List<String>> result) {
        for(int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }
    }

    public static void main(String[] args) {
        LeetsMateApplication app = new LeetsMateApplication();
        app.run();
    }
}