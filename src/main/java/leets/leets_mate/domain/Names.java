package leets.leets_mate.domain;

import java.util.List;
import java.util.Objects;

public class Names {

    private final List<Name> names;

    public Names(List<Name> names) {
        this.names = names;
    }

    public int countNames() {
        return names.size();
    }

    public List<Name> findByIndexes(List<Integer> indexes) {
        return indexes.stream()
                .map(names::get)
                .toList();
    }

    public List<String> names() {
        return names.stream()
                .map(Name::asString)
                .toList();
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
