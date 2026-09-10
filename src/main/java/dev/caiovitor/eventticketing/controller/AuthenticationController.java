package dev.caiovitor.eventticketing.controller;

import dev.caiovitor.eventticketing.dto.*;
import dev.caiovitor.eventticketing.entity.User;
import dev.caiovitor.eventticketing.mapper.UserMapper;
import dev.caiovitor.eventticketing.service.AuthenticationService;
import dev.caiovitor.eventticketing.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;
    private final RefreshTokenService refreshTokenService;


    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginDTO dto) {

        TokenResponseDTO login = authenticationService.login(dto);
        return ResponseEntity.ok(login);

    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserCreateDTO dto) {

        User user = authenticationService.registerUser(userMapper.toEntity(dto));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDTO> refreshToken(@Valid @RequestBody RefreshTokenRequestDTO dto) {

        TokenResultDTO tokenResult = refreshTokenService.rotateToken(dto.refreshToken());

        return ResponseEntity.ok(new TokenResponseDTO(
                            tokenResult.accessToken(),
                            tokenResult.refreshToken()
        ));

    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody RefreshTokenRequestDTO dto) {

        refreshTokenService.logout(dto.refreshToken());
        return ResponseEntity.noContent().build();

    }

}

