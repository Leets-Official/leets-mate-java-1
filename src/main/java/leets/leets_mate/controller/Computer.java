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

        List<Pair> pairs = pairMatcher.match(names);
        outputView.printPairMatchNotice();
        pairs.forEach(pair -> outputView.printPairMatchResult(pair.names()));
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
        new PairCountValidator().validate(pairCount, namesCount);
        return new PairCount(pairCount);
    }
}
