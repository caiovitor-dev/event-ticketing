package dev.caiovitor.eventticketing.service;

import dev.caiovitor.eventticketing.entity.Role;
import dev.caiovitor.eventticketing.enums.RoleName;
import dev.caiovitor.eventticketing.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Optional<Role> findByName(RoleName name){
        return  roleRepository.findByName(name);
    }
}
