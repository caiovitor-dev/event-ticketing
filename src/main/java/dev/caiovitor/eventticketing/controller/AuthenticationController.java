package dev.caiovitor.eventticketing.controller;

import dev.caiovitor.eventticketing.dto.LoginDTO;
import dev.caiovitor.eventticketing.dto.TokenResponseDTO;
import dev.caiovitor.eventticketing.dto.UserCreateDTO;
import dev.caiovitor.eventticketing.entity.User;
import dev.caiovitor.eventticketing.mapper.UserMapper;
import dev.caiovitor.eventticketing.service.AuthenticationService;
import dev.caiovitor.eventticketing.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login (@Valid @RequestBody LoginDTO dto){

        TokenResponseDTO login = authenticationService.login(dto);
        return  ResponseEntity.ok(login);

    }
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserCreateDTO dto){

        User user = authenticationService.registerUser(userMapper.toEntity(dto));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
}
