package dev.caiovitor.eventticketing.dto;


import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record RefreshTokenRequest(
        @NotBlank(message = "token cannot be blank.") UUID refreshToken
)
{
}
