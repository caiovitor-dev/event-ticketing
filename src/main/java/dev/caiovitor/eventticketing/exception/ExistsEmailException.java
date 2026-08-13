package dev.caiovitor.eventticketing.exception;

public class ExistsEmailException extends RuntimeException {
    public ExistsEmailException(String message) {
        super(message);
    }
}
