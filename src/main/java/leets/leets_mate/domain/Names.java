package leets.leets_mate.domain;

import java.util.List;
import java.util.Objects;

public class Names {

    private static final int MIN_SIZE = 2;
    private static final String ERROR_FORMAT = "[ERROR] 이름은 %d개 이상이어야 합니다.";

    private final List<Name> names;

    public Names(List<Name> names) {
        validate(names);
        this.names = names;
    }

    private void validate(List<Name> names) {
        if (names.size() < MIN_SIZE) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT, MIN_SIZE));
        }
    }

    public int countNames() {
        return names.size();
    }

    public List<Name> findByIndexes(List<Integer> indexes) {
        return indexes.stream()
                .map(names::get)
                .toList();
    }

    public List<Name> findByIndexes(RandomNumbers randomNumbers) {
        return randomNumbers.findNames(names);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Names names = (Names) o;
        return Objects.equals(this.names, names.names);
    }

    @Override
    public int hashCode() {
        return Objects.hash(names);
    }
}
