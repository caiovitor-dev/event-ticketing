package dev.caiovitor.eventticketing.entity;

import dev.caiovitor.eventticketing.enums.RoleName;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;
import java.util.UUID;

@Table(name = "roles")
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",nullable = false,updatable = false)
    private UUID id;

    @Column(name = "name",nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

}
