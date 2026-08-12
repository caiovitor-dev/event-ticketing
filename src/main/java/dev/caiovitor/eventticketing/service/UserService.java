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

    public User createUser(User user){
        return userRepository.save(user);
    }

}
