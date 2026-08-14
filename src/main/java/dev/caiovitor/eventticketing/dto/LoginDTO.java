package dev.caiovitor.eventticketing.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
       @NotBlank(message = "Email cannot be blank") String email,
       @NotBlank(message = "Email cannot be blank") String password
) {
}
