package leets.leets_mate.domain;

import java.util.List;
import java.util.stream.IntStream;

public class RandomNumbers {

    private final List<Integer> values;

    public RandomNumbers(List<Integer> values) {
        this.values = values;
    }

    public List<RandomNumbers> splitBySize(int size) {
        int length = values.size();
        return IntStream.iterate(0, i -> i < length, i -> i + size)
                .mapToObj(i -> values.subList(i, Math.min(i + size, length)))
                .map(RandomNumbers::new)
                .toList();
    }

    public List<Name> findNames(List<Name> names) {
        return values.stream()
                .map(names::get)
                .toList();
    }
}
