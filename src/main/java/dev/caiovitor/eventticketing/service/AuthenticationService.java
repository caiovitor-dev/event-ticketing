package dev.caiovitor.eventticketing.service;

import dev.caiovitor.eventticketing.dto.LoginDTO;
import dev.caiovitor.eventticketing.dto.TokenResponseDTO;
import dev.caiovitor.eventticketing.entity.Role;
import dev.caiovitor.eventticketing.entity.User;
import dev.caiovitor.eventticketing.enums.RoleName;
import dev.caiovitor.eventticketing.exception.RoleNotFoundException;
import dev.caiovitor.eventticketing.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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


    public TokenResponseDTO login(LoginDTO dto){

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));

        UserDetails userDetails = (UserDetails) authenticate.getPrincipal();

        String token = jwtService.generateAccessToken(userDetails);

        return new TokenResponseDTO(token);
    }

    @Transactional
    public User registerUser(User user) {

        Role role = roleService.findByName(RoleName.ROLE_CLIENT).orElseThrow(() -> new RoleNotFoundException("Role Not Found"));
        String encode = passwordEncoder.encode(user.getPassword());

        user.setPassword(encode);
        user.setRoles(Set.of(role));

        return userService.createUser(user);

    }

}
