package dev.caiovitor.eventticketing.service;

import dev.caiovitor.eventticketing.entity.User;
import dev.caiovitor.eventticketing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public boolean existsByEmail(String email){
        return  userRepository.existsByEmail(email);
    }

    public boolean existsByCpf(String cpf){
        return  userRepository.existsByCpf(cpf);
    }

}
