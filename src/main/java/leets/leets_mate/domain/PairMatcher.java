package leets.leets_mate.domain;

import java.util.List;

public class PairMatcher {

    private final PairCount pairCount;
    private final RandomNumbersGenerator randomNumbersGenerator;

    public PairMatcher(PairCount pairCount, RandomNumbersGenerator randomNumbersGenerator) {
        this.pairCount = pairCount;
        this.randomNumbersGenerator = randomNumbersGenerator;
    }

    public List<Pair> match(Names names) {
        List<List<Integer>> randomNumbers = randomNumbersGenerator.randomNumbers(names.countNames(), pairCount.value());
        return randomNumbers.stream()
                .map(names::findByIndexes)
                .map(Pair::new)
                .toList();
    }
}
