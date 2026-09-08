package dev.caiovitor.eventticketing.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RefreshTokenRequestDTO(
       @NotNull(message = "Token cannot be empty.")
       UUID refreshToken
)
{
}
