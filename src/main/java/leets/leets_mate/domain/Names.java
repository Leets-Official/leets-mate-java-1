package leets.leets_mate.domain;

import java.util.List;

public class Names {

    private final List<Name> names;

    public Names(List<Name> names) {
        this.names = names;
    }

    public int countNames() {
        return names.size();
    }
}
