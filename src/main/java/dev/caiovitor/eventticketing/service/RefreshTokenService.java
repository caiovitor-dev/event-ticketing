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

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Value("${jwt.refresh-token-expiration}")
    private Duration refreshTokenExpiration;

    public String createRefreshToken(User user){


        RefreshToken refreshToken = new RefreshToken();
        String rawToken = UUID.randomUUID().toString();

        refreshToken.setUser(user);
        refreshToken.setToken(passwordEncoder.encode(rawToken));
        refreshToken.setExpiresAt(LocalDateTime.now().plus(refreshTokenExpiration));

        refreshTokenRepository.save(refreshToken);

        return rawToken;

    }

  
}
