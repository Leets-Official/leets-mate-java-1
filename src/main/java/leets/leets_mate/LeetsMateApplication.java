package leets.leets_mate;

import leets.leets_mate.exception.InvalidInputException;

import java.util.*;

public class LeetsMateApplication {
    private static final String START_MESSAGE = "[Leets 오늘의 짝에게]를 시작합니다.\n";
    private static final String GOODBYE_MESSAGE = "추첨을 완료하였습니다.\n자리를 이동해 서로에게 인사해주세요.";


    public void run(){
        Scanner sc = new Scanner(System.in);

        System.out.println(START_MESSAGE);
        System.out.println("멤버의 이름을 입력해 주세요. (, 로 구분)");
        String members = sc.nextLine();

        checkHasNoEnglish(members);
        List<String> memberList = parseMembers(members);

        int maximumGroupSize = getMaximumGroupSize(sc);


        int memberCount = memberNumber(memberList);

        checkDataValidity(memberCount, maximumGroupSize);

        printResultAndMessage(memberList, maximumGroupSize);
        while (askForRestart(sc)) {
            System.out.println("--------------------------------");
            printResultAndMessage(memberList, maximumGroupSize);
        }
        sc.close();
    }

    public List<String> parseMembers(String members) {
        return Arrays.asList(members.split(","));
    }
    private int getMaximumGroupSize(Scanner sc) {
        System.out.println("\n최대 짝 수를 입력해 주세요.");
        int maximumGroupSize = Integer.parseInt(sc.nextLine());
        if (maximumGroupSize <= 0) {
            throw new InvalidInputException("[ERROR] 최대 짝 수는 0보다 커야 합니다");
        }
        return maximumGroupSize;
    }
    public int memberNumber(List<String> members) {
        return members.size();
    }

    public void checkHasNoEnglish(String members){
        if (members.matches(".*[a-zA-Z].*")) {
            throw new InvalidInputException("[ERROR] 이름은 한글로 입력해야 합니다");
        }
        if (members.isEmpty()) {
            throw new InvalidInputException("[ERROR] 멤버의 이름을 입력해 주세요");
        }
    }

    public void checkDataValidity(int memberCount, int maximumGroupSize){
        if (maximumGroupSize <= 0) {
            throw new InvalidInputException("[ERROR] 최대 짝 수는 0보다 커야 합니다");
        }
        if (maximumGroupSize > memberCount) {
            throw new InvalidInputException("[ERROR] 최대 짝 수는 이름의 갯수보다 클 수 없습니다");
        }
    }

    public List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {
        Collections.shuffle(memberList);

        List<List<String>> resultList = new ArrayList<>();

        int column = maximumGroupSize;
        int row = (memberList.size() + column - 1) / column;


        for (int i = 0; i < row; i++) {
            resultList.add(new ArrayList<>());
            for (int j = i*column; j < column*(i+1); j++) {
                if(j<memberList.size()) resultList.get(i).add(memberList.get(j));
            }
        }
        return resultList;
    }

    public void printResult(List<List<String>> result) {
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<result.size(); i++){
            sb.append("[ ");
            for(int j=0; j<result.get(i).size(); j++){
                sb.append(result.get(i).get(j));
                if(j<result.get(i).size()-1) sb.append(" | ");
            }
            sb.append(" ]");
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public void printResultAndMessage(List<String> memberList, int maximumGroupSize) {
        List<List<String>> lists = generateRandomGroups(memberList, maximumGroupSize);
        System.out.println("\n오늘의 짝 추천 결과입니다.");
        printResult(lists);
        System.out.println(GOODBYE_MESSAGE);
    }
    public boolean askForRestart(Scanner sc) {
        while (true) {
            System.out.print("다시 구성하시겠습니까? (y or n): ");
            String retry = sc.nextLine().trim().toLowerCase();
            if (retry.equals("n")) {
                System.out.println("자리를 이동해 서로에게 인사해주세요.");
                return false;
            } else if (retry.equals("y")) {
                return true;
            } else {
                throw new InvalidInputException("[ERROR] 잘못된 입력입니다. 'y' 또는 'n'을 입력해주세요.");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        LeetsMateApplication app = new LeetsMateApplication();
        app.run();
    }
}