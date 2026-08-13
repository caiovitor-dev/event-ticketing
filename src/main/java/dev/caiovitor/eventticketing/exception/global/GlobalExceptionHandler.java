package dev.caiovitor.eventticketing.exception.global;

import dev.caiovitor.eventticketing.dto.ErrorResponseDTO;
import dev.caiovitor.eventticketing.exception.ExistsCpfException;
import dev.caiovitor.eventticketing.exception.ExistsEmailException;
import dev.caiovitor.eventticketing.exception.RoleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO>  handlerRoleNotFound(RoleNotFoundException e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponseDTO(
                        HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        LocalDateTime.now()));
    }
    @ExceptionHandler(ExistsEmailException.class)
    public ResponseEntity<ErrorResponseDTO>  handlerExistsEmail(ExistsEmailException e){

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponseDTO(
                        HttpStatus.CONFLICT.value(),
                        e.getMessage(),
                        LocalDateTime.now()));
    }
    @ExceptionHandler(ExistsCpfException.class)
    public ResponseEntity<ErrorResponseDTO>  handlerExistsCpf(ExistsCpfException e){

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponseDTO(
                        HttpStatus.CONFLICT.value(),
                        e.getMessage(),
                        LocalDateTime.now()));
    }
}
