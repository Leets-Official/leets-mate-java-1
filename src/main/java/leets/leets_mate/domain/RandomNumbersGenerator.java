package leets.leets_mate.domain;

import java.util.List;
import java.util.Random;

public class RandomNumbersGenerator {

    private final Random random = new Random();

    public List<Integer> randomNumbers(int pairCount, int bound) {
        return random.ints(0, bound)
                .distinct()
                .limit(pairCount)
                .boxed()
                .toList();
    }
}
