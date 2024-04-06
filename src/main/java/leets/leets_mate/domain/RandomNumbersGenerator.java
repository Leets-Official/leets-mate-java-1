package leets.leets_mate.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class RandomNumbersGenerator {

    public List<List<Integer>> randomNumbers(int bound, int size) {
        List<Integer> integers = new ArrayList<>(IntStream.range(0, bound)
                .boxed()
                .toList());
        Collections.shuffle(integers);

        List<List<Integer>> randomIndexes = new ArrayList<>();
        for (int index = 0; index < bound; index += size) {
            int end = Math.min(index + size, bound);
            randomIndexes.add(integers.subList(index, end));
        }
        return randomIndexes;
    }
}
