package dev.caiovitor.eventticketing.entity;

import dev.caiovitor.eventticketing.enums.EventSessionStatus;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "event_sessions")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Setter
@Getter
@EqualsAndHashCode(of = "id")
public class EventSession {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false,updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id",nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id",nullable = false)
    private Venue venue;

    @Enumerated(EnumType.STRING)
    @Column(name = "session_status",nullable = false,length = 20)
    private EventSessionStatus sessionStatus;

    @Column(name = "starts_at",nullable = false)
    private LocalDateTime startsAt;

    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;



}
