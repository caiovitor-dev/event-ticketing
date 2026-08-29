package dev.caiovitor.eventticketing.dto;

public record TokenResponseDTO (
        String accessToken,
        String refreshToken
){
}
