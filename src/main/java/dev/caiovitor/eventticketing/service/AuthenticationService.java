package dev.caiovitor.eventticketing.service;

import dev.caiovitor.eventticketing.dto.CreatedRefreshTokenResultDTO;
import dev.caiovitor.eventticketing.dto.LoginDTO;
import dev.caiovitor.eventticketing.dto.TokenResponseDTO;
import dev.caiovitor.eventticketing.entity.Role;
import dev.caiovitor.eventticketing.entity.User;
import dev.caiovitor.eventticketing.enums.RoleName;
import dev.caiovitor.eventticketing.exception.ExistsCpfException;
import dev.caiovitor.eventticketing.exception.ExistsEmailException;
import dev.caiovitor.eventticketing.exception.RoleNotFoundException;
import dev.caiovitor.eventticketing.security.CustomUserDetails;
import dev.caiovitor.eventticketing.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;



@RequiredArgsConstructor
@Service
public class AuthenticationService {


    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final RefreshTokenService refreshTokenService;

    public TokenResponseDTO login(LoginDTO dto)  {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.email(),
                        dto.password()
                )
        );

        CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();
        String token = jwtService.generateAccessToken(userDetails);

        CreatedRefreshTokenResultDTO refreshToken =
                refreshTokenService.createRefreshToken(userDetails.getUser());

        return new TokenResponseDTO(token,refreshToken.rawToken());
    }

    @Transactional
    public User registerUser(User user) {

        if(userService.existsByEmail(user.getEmail())){
            throw new ExistsEmailException("This email is already registered");
        }

        if(userService.existsByCpf(user.getCpf())){
            throw new ExistsCpfException("This cpf is already registered");
        }

        Role role = roleService.findByName(RoleName.ROLE_CLIENT).orElseThrow(() -> new RoleNotFoundException("Role Not Found"));
        String encode = passwordEncoder.encode(user.getPassword());

        user.setPassword(encode);
        user.setRoles(Set.of(role));

        return userService.createUser(user);

    }

}
