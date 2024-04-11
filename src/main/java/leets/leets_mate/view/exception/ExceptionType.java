package leets.leets_mate.view.exception;

public interface ExceptionType<T> {
    String getMessage();

    T getException();
}