package dev.caiovitor.eventticketing.repository;

import dev.caiovitor.eventticketing.entity.Role;
import dev.caiovitor.eventticketing.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {


    Optional<Role> findByName(RoleName name);
}
