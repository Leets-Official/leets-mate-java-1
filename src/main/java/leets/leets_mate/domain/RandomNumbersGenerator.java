package leets.leets_mate.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class RandomNumbersGenerator {

    public List<Integer> randomNumbers(int bound) {
        List<Integer> list = new ArrayList<>(IntStream.range(0, bound)
                .boxed()
                .toList());
        Collections.shuffle(list);
        return list;
    }
}
