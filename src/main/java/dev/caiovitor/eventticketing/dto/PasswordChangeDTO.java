package dev.caiovitor.eventticketing.dto;

import jakarta.validation.constraints.NotBlank;

public record PasswordUpdateDTO(
        @NotBlank(message = "New password cannot be blank.")
        String newPassword,

        @NotBlank(message = "Current password cannot be blank.")
        String currentPassword
) {
}
