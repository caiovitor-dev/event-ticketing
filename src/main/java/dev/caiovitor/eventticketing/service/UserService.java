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

}
