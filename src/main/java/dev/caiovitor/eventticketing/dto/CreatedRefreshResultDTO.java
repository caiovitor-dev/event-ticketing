package dev.caiovitor.eventticketing.dto;

import dev.caiovitor.eventticketing.entity.RefreshToken;

public record CreatedRefreshResult(RefreshToken entity, String rawToken){
}
