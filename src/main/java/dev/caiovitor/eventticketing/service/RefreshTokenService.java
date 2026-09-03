package dev.caiovitor.eventticketing.service;

import dev.caiovitor.eventticketing.entity.RefreshToken;
import dev.caiovitor.eventticketing.entity.User;
import dev.caiovitor.eventticketing.repository.RefreshTokenRepository;
import dev.caiovitor.eventticketing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    @Value("${jwt.refresh-token-expiration}")
    private Duration refreshTokenExpiration;

    public String createRefreshToken(User user) {

        RefreshToken refreshToken = new RefreshToken();
        String rawToken = UUID.randomUUID().toString();

        refreshToken.setUser(user);
        refreshToken.setToken(tokenEncoder(rawToken));
        refreshToken.setExpiresAt(LocalDateTime.now().plus(refreshTokenExpiration));

        refreshTokenRepository.save(refreshToken);

        return rawToken;

    }

    public String tokenEncoder(String token) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(token.getBytes(StandardCharsets.UTF_8));

            return HexFormat.of().formatHex(hashBytes);

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("algorithm not found");
        }
    }

}
