package leets.leets_mate.controller;

import leets.leets_mate.domain.*;
import leets.leets_mate.view.InputView;
import leets.leets_mate.view.OutputView;

import java.util.List;

public class Computer {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        outputView.printStartNotice();
        Names names = readNames();
        PairCount pairCount = readPairCount(names.countNames());

        PairMatcher pairMatcher = new PairMatcher(pairCount, new RandomNumbersGenerator());
        matchPairs(pairMatcher, names);
        outputView.printEndNotice();
    }

    private void matchPairs(PairMatcher pairMatcher, Names names) {
        List<Pair> pairs = pairMatcher.match(names);
        printPairMatchResult(pairs);

        RetryOpinion retryOpinion = readRetry();
        if (retryOpinion.isContinue()) {
            outputView.printBreakLine();
            matchPairs(pairMatcher, names);
        }
    }

    private void printPairMatchResult(List<Pair> pairs) {
        outputView.printPairMatchResultNotice();
        pairs.forEach(pair -> outputView.printPairMatchResult(pair.names()));
        outputView.printPairMatchFinishNotice();
    }

    private Names readNames() {
        List<String> rawNames = inputView.readNames();
        List<Name> names = rawNames.stream()
                .map(Name::new)
                .toList();
        return new Names(names);
    }

    private PairCount readPairCount(int namesCount) {
        int pairCount = inputView.readPairCount();
        try {
            new PairCountValidator().validate(pairCount, namesCount);
            return new PairCount(pairCount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readPairCount(namesCount);
        }
    }

    private RetryOpinion readRetry() {
        String input = inputView.readRetry();
        return RetryOpinion.match(input);
    }
}
