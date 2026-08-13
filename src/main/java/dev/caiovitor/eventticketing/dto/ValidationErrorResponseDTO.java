package dev.caiovitor.eventticketing.dto;


import java.time.LocalDateTime;
import java.util.Map;

public record ValidationErrorResponseDTO(
        int status,
        String message,
        LocalDateTime timesStamp,
        Map<String,String> errors
) {
}
