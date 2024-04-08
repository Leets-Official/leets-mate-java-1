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
        RandomNumbers randomNumbers = randomNumbersGenerator.randomNumbers(names.countNames());
        List<RandomNumbers> splitRandomNumbers = randomNumbers.splitBySize(pairCount.value());
        return splitRandomNumbers.stream()
                .map(names::findByIndexes)
                .map(Pair::new)
                .toList();
    }
}
