package leets.leets_mate;

import java.util.*;

public class LeetsMateApplication {

    // 동작 함수입니다.
    public void run() throws Exception {
        Boolean playagain = true;
        while(playagain) {
            Scanner sc = new Scanner(System.in);
            System.out.println("참석자들의 이름을 입력해 주세요. (,로 구분)");
            String str = sc.nextLine();
            checkHasNoEnglish(str);
            List<String> memberList = parseMembers(str);
            System.out.println(Arrays.deepToString(memberList.toArray()));

            int memberCount = memberNumber(memberList);

            System.out.println("최대 짝 수를 입력해 주세요");
            int maximumGroupSize = sc.nextInt();
            checkDataValidity(memberCount, maximumGroupSize);   //멤버수와 최대 짝수 데이터가 유효한지 검사

            List<List<String>> result = generateRandomGroups(memberList, maximumGroupSize);
            System.out.println("오늘의 짝 추천 결과입니다.");
            printResult(result);
            //김성민,조혜원,노정완,김혜진,양태석, 전시현, 아아

            System.out.println("추천을 완료했습니다.\n다시 구성하시겠습니까? (y or n):");

            char again = sc.next().charAt(0);
            if(again == 'n' || again == 'N'){
                playagain = false;
            }
        }
        System.out.println("자리를 이동해 서로에게 인사해주세요");

    }

    // 문자열로된 멤버들을 리스트로 분리하는 함수입니다.
    public List<String> parseMembers(String members) {
        //String[] memberList = members.split(",");
        /*
        for(int i = 0; i<members.length()){
            if(members[i])
        }*/

        members = members.replaceAll(", ", ",");
        List<String> memberList = Arrays.asList(members.split(","));
        return memberList;
    }

    // 총 멤버수를 반환합니다.
    public int memberNumber(List<String> members) {
        return members.size();
    }

    // 멤버 문자열에 영어가 있는지 검사합니다. 영어가 있다면 예외 출력
    public void checkHasNoEnglish(String members) throws Exception {
        if(members.matches(".*[a-zA-Z].*")) {//영어가 포함되면
            System.out.println("has english");
            throw new Exception("has english");
        }
    }

    // 멤버수와 최대 짝수 데이터가 유효한지 검사하는 함수입니다. 유효하지 않다면 예외 출력
    public void checkDataValidity(int memberCount, int maximumGroupSize) throws Exception {
        if(memberCount<maximumGroupSize){
            throw new Exception("num of members < maximum group size");
        }
    }

    // 랜덤 짝꿍 추첨하는 함수 입니다.
    //멤버 수 9명, maximumGroupSize : 3 -> 3개조
    //멤버 수 9명, maximumGroupSize:4 -> 4*2+1, 4,
    public List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {
        int size = memberList.size();
        Random r = new Random();
        int index;

        ArrayList<String> selected = new ArrayList<String>();

        for (int i = 0; i<size; i++){
            index = r.nextInt(size); //0 - size-1s
            while(selected.contains(memberList.get(index))){
                index = r.nextInt(size); //0 - size-1
            }
            selected.add(memberList.get(index));
        }//randomly select members
        //8/2 = 2, 2, 2, 2
        // 9/2 = 2, 2, 2, 2, 1
        // 8/4 = 4, 4
        // 9/4 = 4, 3, 2
        // 10/4 = 4, 4, 2 at least 2 people are in a group
        int[] groupnum = new int[size/maximumGroupSize+10];
        int times;
        if (size%maximumGroupSize==0){
            times = size/maximumGroupSize;  //the number of total groups
        }
        else{
            times = size/maximumGroupSize+1;    //the number of total groups
        }


        int left = size;
        //get num of members per group
        for(int i = 0; i<times; i++){
            if(left>=maximumGroupSize){
                groupnum[i] = maximumGroupSize;
                left-=maximumGroupSize;
            }
            else{
                groupnum[i] = left;
            }
        }
        //at least 2 people in a group
        if(left==1 && maximumGroupSize!=1 && maximumGroupSize !=2){
            //left alone
            groupnum[times-2]--;
            groupnum[times-1]++; //change group
        }
        List<List<String>> result = new ArrayList<>();

        //add member names as the num of members with groupnum[i]
        int indexOfSelected = 0;
        for(int i = 0; i<times; i++){
            List<String> innerList = new ArrayList<>();
            for(int j = 0; j<groupnum[i]; j++){
                innerList.add(selected.get(indexOfSelected));   //add in the same group
                indexOfSelected++;
            }// 4 4 4 3 2
            result.add(innerList);  //add the group to the result
        }


        return result;
    }

    // 결과를 프린트 하는 함수입니다.
    public void printResult(List<List<String>> result) {
        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        LeetsMateApplication app = new LeetsMateApplication();
        app.run();
    }
}