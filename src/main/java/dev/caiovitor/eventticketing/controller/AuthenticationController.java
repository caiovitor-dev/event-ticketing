package dev.caiovitor.eventticketing.controller;

import dev.caiovitor.eventticketing.dto.*;
import dev.caiovitor.eventticketing.entity.User;
import dev.caiovitor.eventticketing.mapper.UserMapper;
import dev.caiovitor.eventticketing.service.AuthenticationService;
import dev.caiovitor.eventticketing.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    PasswordEncoder passwordEncoder;

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
    public ResponseEntity<TokenResponseDTO> refreshToken(@RequestBody RefreshTokenRequestDTO dto) {

        return refreshTokenService.findByToken(dto.refreshToken()).map(token->{

           TokenResultDTO tokenResult = refreshTokenService.rotateToken(token);

            TokenResponseDTO tokens = new TokenResponseDTO(
                    tokenResult.accessToken(),
                    tokenResult.refreshToken()
            );

            return ResponseEntity.ok(tokens);

        }).orElse(ResponseEntity.notFound().build());

    }

}

