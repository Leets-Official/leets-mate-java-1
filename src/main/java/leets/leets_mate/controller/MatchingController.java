package leets.leets_mate.controller;

import leets.leets_mate.domain.Member;
import leets.leets_mate.service.GroupService;
import leets.leets_mate.view.InputView;
import leets.leets_mate.view.OutputView;

import java.util.List;

public class MatchingController {
    GroupService groupService = new GroupService();

    public void run() {
        String[] members = InputView.readMember();
        int maxGroupSize = InputView.readMaxGroup(members);

        List<Member> membersOfList = Member.parseMembers(members);

        boolean retry = true;
        while (retry) {
            List<List<Member>> groupsOfList = groupService.generateRandomGroups(membersOfList, maxGroupSize);

            OutputView.printResult(groupsOfList);
            OutputView.printCompletionMessage();

            OutputView.printCanYouRetry();
            retry = InputView.readRetry();

            if (retry == true) {
                OutputView.printLine();
            }
        }

        OutputView.printDontWantRetry();
    }
}
