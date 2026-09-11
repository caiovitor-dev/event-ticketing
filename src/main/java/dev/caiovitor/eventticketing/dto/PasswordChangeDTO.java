package dev.caiovitor.eventticketing.dto;

import jakarta.validation.constraints.NotBlank;

public record PasswordChangeDTO(
        @NotBlank(message = "New password cannot be blank.")
        String newPassword,

        @NotBlank(message = "Current password cannot be blank.")
        String currentPassword
) {
}
