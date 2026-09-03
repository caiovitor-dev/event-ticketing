package dev.caiovitor.eventticketing.exception.global;

import dev.caiovitor.eventticketing.dto.ErrorResponseDTO;
import dev.caiovitor.eventticketing.dto.ValidationErrorResponseDTO;
import dev.caiovitor.eventticketing.exception.ExistsCpfException;
import dev.caiovitor.eventticketing.exception.ExistsEmailException;
import dev.caiovitor.eventticketing.exception.RoleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponseDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException e){

        Map<String,String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
                    errors.put(fieldError.getField(),fieldError.getDefaultMessage());
                });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ValidationErrorResponseDTO(
                        HttpStatus.BAD_REQUEST.value(),
                       "Validation Error",
                        LocalDateTime.now(),
                        errors));
    }


    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO>  handleRoleNotFound(RoleNotFoundException e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponseDTO(
                        HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        LocalDateTime.now()));
    }
    @ExceptionHandler(ExistsEmailException.class)
    public ResponseEntity<ErrorResponseDTO>  handleExistsEmail(ExistsEmailException e){

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponseDTO(
                        HttpStatus.CONFLICT.value(),
                        e.getMessage(),
                        LocalDateTime.now()));
    }
    @ExceptionHandler(ExistsCpfException.class)
    public ResponseEntity<ErrorResponseDTO>  handleExistsCpf(ExistsCpfException e){

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponseDTO(
                        HttpStatus.CONFLICT.value(),
                        e.getMessage(),
                        LocalDateTime.now()));
    }
}
