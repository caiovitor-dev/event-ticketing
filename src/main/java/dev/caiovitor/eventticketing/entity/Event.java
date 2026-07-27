package dev.caiovitor.eventticketing.entity;

import dev.caiovitor.eventticketing.enums.EventStatus;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "events")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",nullable = false,updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "description",nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_status",nullable = false,length = 20)
    private EventStatus eventStatus;

    @JoinColumn(name = "organizer_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User organizer;

    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;
}
