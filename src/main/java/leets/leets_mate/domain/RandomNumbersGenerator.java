package leets.leets_mate.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class RandomNumbersGenerator {

    public RandomNumbers randomNumbers(int bound) {
        List<Integer> randomNumbers = new ArrayList<>(IntStream.range(0, bound)
                .boxed()
                .toList());
        Collections.shuffle(randomNumbers);
        return new RandomNumbers(randomNumbers);
    }
}
