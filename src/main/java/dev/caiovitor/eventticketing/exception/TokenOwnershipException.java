package dev.caiovitor.eventticketing.exception;

public class UserNotOwnerToken extends RuntimeException {
    public UserNotOwnerToken(String message) {
        super(message);
    }
}
