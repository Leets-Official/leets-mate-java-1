package leets.leets_mate.domain;

import java.util.Objects;

public class Name {

    private static final String ERROR_FORMAT = "[ERROR] 이름은 %d자 이하여야 합니다.";
    private static final int MAX_LENGTH = 5;

    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT, MAX_LENGTH));
        }
    }

    public String asString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(this.name, name.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
