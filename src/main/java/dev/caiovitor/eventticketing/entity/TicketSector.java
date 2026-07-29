package dev.caiovitor.eventticketing.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "ticket_sectors")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class TicketSector {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false,updatable = false)
    private UUID id;

    @Column(name = "name",nullable = false , length = 100)
    private String name;

    @JoinColumn(name = "event_session_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private EventSession session;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;




}
