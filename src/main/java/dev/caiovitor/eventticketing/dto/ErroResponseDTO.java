package dev.caiovitor.eventticketing.dto;

import java.time.LocalDateTime;

public record ErroResponseDTO (int status, String message, LocalDateTime timesStamp) {
}
