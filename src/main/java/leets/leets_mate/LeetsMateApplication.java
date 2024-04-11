package leets.leets_mate;

import leets.leets_mate.domain.*;
import leets.leets_mate.view.Inputs;
import leets.leets_mate.view.Prints;
import leets.leets_mate.view.RetryOpinion;

import java.util.List;

public class LeetsMateApplication {
    Inputs inputs = new Inputs();
    GroupGenerator groupGenerator = new GroupGenerator();
    Prints prints = new Prints();

    public void run() {
        prints.opening();

        Members members = inputs.members();
        MaxGroupSize maxGroupSize = inputs.maxGroupSize();
        maxGroupSize.checkMemberCountBelowMaxGroupSize(members.getCount());

        boolean isDone = false;
        while (!isDone) {
            List<List<String>> matchedGroups = groupGenerator.generateRandomGroups(members, maxGroupSize);
            prints.result(matchedGroups);
            if (inputs.retryOpinion() == RetryOpinion.NO) {
                isDone = true;
            } else if (inputs.retryOpinion() == RetryOpinion.YES) {
                prints.line();
            }
        }
        prints.ending();
    }

    public static void main(String[] args) {
        LeetsMateApplication app = new LeetsMateApplication();
        app.run();
    }
}

















