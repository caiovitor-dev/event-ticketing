package dev.caiovitor.eventticketing.exception;

public class CpfExistsException extends RuntimeException {
    public CpfExistsException(String message) {
        super(message);
    }
}
