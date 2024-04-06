package leets.leets_mate.domain;

import java.util.ArrayList;
import java.util.List;

public class PairMatcher {

    private final int pairCount;
    private final RandomNumbersGenerator randomNumbersGenerator;

    public PairMatcher(int pairCount, RandomNumbersGenerator randomNumbersGenerator) {
        this.pairCount = pairCount;
        this.randomNumbersGenerator = randomNumbersGenerator;
    }

    public List<Pair> match(Names names) {
        List<List<Integer>> randomNumbers = randomNumbersGenerator.randomNumbers(names.countNames(), pairCount);

        List<Pair> pairs = new ArrayList<>();
        for (List<Integer> randomNumber : randomNumbers) {
            List<Name> pairNames = names.findByIndexes(randomNumber);
            Pair pair = new Pair(pairNames);
            pairs.add(pair);
        }
        return pairs;
    }
}
