package leets.leets_mate.domain;

import java.util.List;
import java.util.Objects;

public class Pair {

    private final Names names;

    public Pair(List<Name> names) {
        this.names = new Names(names);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Objects.equals(names, pair.names);
    }

    @Override
    public int hashCode() {
        return Objects.hash(names);
    }
}
