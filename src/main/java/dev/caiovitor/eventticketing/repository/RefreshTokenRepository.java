package dev.caiovitor.eventticketing.repository;

import dev.caiovitor.eventticketing.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    public Optional<RefreshToken> findByToken(String token);

    @Modifying
    @Query("""
           UPDATE RefreshToken u 
           SET u.revokedAt = CURRENT_TIMESTAMP 
           WHERE u.revokedAt IS NULL
           AND u.user.id =:id
         """)
    public void revokeUserTokens(@Param("id")UUID id);

 
    @Modifying
    @Query("""
            DELETE RefreshToken u
            WHERE u.revokedAt <=:cutoffDate
            """)
    void deleteRevokedTokens(@Param("cutoffDate")LocalDateTime cutoffDate);

}
