package leets.leets_mate.domain;

import java.util.ArrayList;
import java.util.List;

public class RandomNumbers {

    private final List<Integer> values;

    public RandomNumbers(List<Integer> values) {
        this.values = values;
    }

    public List<RandomNumbers> splitBySize(int size) {
        int length = values.size();
        List<RandomNumbers> randomIndexes = new ArrayList<>();
        for (int index = 0; index < length; index += size) {
            int end = Math.min(index + size, length);
            List<Integer> randomNumbers = values.subList(index, end);
            randomIndexes.add(new RandomNumbers(randomNumbers));
        }
        return randomIndexes;
    }

    public List<Name> findNames(List<Name> names) {
        return values.stream()
                .map(names::get)
                .toList();
    }
}
