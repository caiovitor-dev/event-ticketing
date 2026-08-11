package dev.caiovitor.eventticketing.service;

import dev.caiovitor.eventticketing.entity.Role;
import dev.caiovitor.eventticketing.entity.User;
import dev.caiovitor.eventticketing.enums.RoleName;
import dev.caiovitor.eventticketing.repository.RoleRepository;
import dev.caiovitor.eventticketing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Transactional
    public User registerUser(User user){

        Optional<Role> role = roleService.findByName(RoleName.ROLE_CLIENT);
        String encode = passwordEncoder.encode(user.getPassword());


        user.setPassword(encode);
        user.setRoles(role.stream().collect(Collectors.toSet()));

        return userRepository.save(user);

    }
}
