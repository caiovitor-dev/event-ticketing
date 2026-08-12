package dev.caiovitor.eventticketing.security;

import dev.caiovitor.eventticketing.dto.ErrorResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalDateTime;


@RequiredArgsConstructor
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {


        ErrorResponseDTO erro = new ErrorResponseDTO(
                HttpStatus.UNAUTHORIZED.value(),
                authException.getMessage(),
                LocalDateTime.now());


        String json = mapper.writeValueAsString(erro);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getWriter().write(json);

    }
}
