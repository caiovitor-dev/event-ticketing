package dev.caiovitor.eventticketing.entity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Table(name = "venues")
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name",nullable = false, length = 155)
    private String name;

    @Embedded
    private Address address;

    @Column(name = "total_capacity", nullable = false)
    private Integer totalCapacity;
}