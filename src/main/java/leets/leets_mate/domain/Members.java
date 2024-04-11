package leets.leets_mate.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Members {
    public List<String> membersList;

    public Members(String memberString) {
        checkNoEnglish(memberString);
        this.membersList = parseMembers(memberString);
    }

    public void shuffleMembers() {
        Collections.shuffle(membersList);
    }

    public int getCount() {
        return membersList.size();
    }

    private List<String> parseMembers(String memberString) {
        return Arrays.asList(memberString.split(","));
    }

    private void checkNoEnglish(String memberString) {
        String regex = ".*[a-zA-Z].*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberString);
        if (matcher.matches()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }
}
