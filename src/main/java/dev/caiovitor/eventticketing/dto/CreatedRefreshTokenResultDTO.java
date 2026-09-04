package dev.caiovitor.eventticketing.dto;

import dev.caiovitor.eventticketing.entity.RefreshToken;

public record CreatedRefreshTokenResultDTO(RefreshToken entity, String rawToken){
}
