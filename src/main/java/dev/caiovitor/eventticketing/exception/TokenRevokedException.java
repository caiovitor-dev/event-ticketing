package dev.caiovitor.eventticketing.exception;

public class TokenRevokedException extends RuntimeException {
  public TokenRevokedException(String message) {
    super(message);
  }
}
