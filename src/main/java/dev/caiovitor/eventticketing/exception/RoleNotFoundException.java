package dev.caiovitor.eventticketing.exception;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException (String message){
        super(message);
    }
}
