package dev.caiovitor.eventticketing.service;

import dev.caiovitor.eventticketing.dto.LoginDTO;
import dev.caiovitor.eventticketing.dto.TokenResponseDTO;
import dev.caiovitor.eventticketing.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AuthenticationService {


    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    
    public TokenResponseDTO login(LoginDTO dto){

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));

        UserDetails userDetails = (UserDetails) authenticate.getPrincipal();

        String token = jwtService.generateAccessToken(userDetails);

        return new TokenResponseDTO(token);
    }

}
