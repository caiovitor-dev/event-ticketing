package dev.caiovitor.eventticketing.repository;

import dev.caiovitor.eventticketing.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
}
