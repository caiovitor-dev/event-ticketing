package dev.caiovitor.eventticketing.service;

import dev.caiovitor.eventticketing.dto.CreatedRefreshTokenResultDTO;
import dev.caiovitor.eventticketing.dto.TokenResultDTO;
import dev.caiovitor.eventticketing.entity.RefreshToken;
import dev.caiovitor.eventticketing.entity.User;
import dev.caiovitor.eventticketing.exception.TokenExpiredException;
import dev.caiovitor.eventticketing.exception.TokenNotFoundException;
import dev.caiovitor.eventticketing.exception.TokenRevokedException;
import dev.caiovitor.eventticketing.exception.TokenOwnershipException;
import dev.caiovitor.eventticketing.repository.RefreshTokenRepository;
import dev.caiovitor.eventticketing.repository.UserRepository;
import dev.caiovitor.eventticketing.security.CustomUserDetails;
import dev.caiovitor.eventticketing.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HexFormat;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final SecurityUtils securityUtils;

    @Value("${jwt.refresh-token-expiration}")
    private Duration refreshTokenExpiration;

    public CreatedRefreshTokenResultDTO createRefreshToken(User user) {

        RefreshToken refreshToken = new RefreshToken();
        String rawToken = UUID.randomUUID().toString();

        refreshToken.setUser(user);
        refreshToken.setToken(tokenEncoder(rawToken));
        refreshToken.setExpiresAt(LocalDateTime.now().plus(refreshTokenExpiration));

        refreshTokenRepository.save(refreshToken);

        return new CreatedRefreshTokenResultDTO(refreshToken,rawToken) ;

    }

    public Optional<RefreshToken> findByToken(UUID rawToken) {
        return refreshTokenRepository.findByToken(tokenEncoder(rawToken.toString()));
    }

    public void logout(UUID rawToken){
        RefreshToken token = findByToken(rawToken)
                .orElseThrow(() ->new TokenNotFoundException("Token not found"));

        if(!token.getUser().equals(securityUtils.getLoggedUser())){
            throw new TokenOwnershipException("Token does not belong to the user!.");
        }

        if(!isRevoked(token)){
            token.setRevokedAt(LocalDateTime.now());
            refreshTokenRepository.save(token);
        }

    }


    public TokenResultDTO rotateToken(UUID rawToken) {

        RefreshToken oldToken = findByToken(rawToken)
                .orElseThrow(() -> new TokenNotFoundException("Token not found"));

        if(isRevoked(oldToken)){
            throw new TokenRevokedException("Refresh token has revoked");
        }

        if (isTokenExpired(oldToken)) {

            oldToken.setRevokedAt(LocalDateTime.now());
            refreshTokenRepository.save(oldToken);

            throw new TokenExpiredException("Log in again.");

        }

        oldToken.setRevokedAt(LocalDateTime.now());
        refreshTokenRepository.save(oldToken);

        CustomUserDetails customUserDetails = new CustomUserDetails(oldToken.getUser());
        String newAccessToken = jwtService.generateAccessToken(customUserDetails);

        CreatedRefreshTokenResultDTO refreshToken = createRefreshToken(oldToken.getUser());
        refreshTokenRepository.save(refreshToken.entity());

        return new TokenResultDTO(newAccessToken,refreshToken.rawToken());
    }

    private String tokenEncoder(String token) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(token.getBytes(StandardCharsets.UTF_8));

            return HexFormat.of().formatHex(hashBytes);

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("algorithm not found");
        }
    }


    private boolean isTokenExpired(RefreshToken token){
       return token.getExpiresAt().isBefore(LocalDateTime.now());
    }

    private boolean isRevoked(RefreshToken token){
        return token.getRevokedAt()!=null;
    }

}
