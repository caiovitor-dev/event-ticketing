package dev.caiovitor.eventticketing.entity;

import dev.caiovitor.eventticketing.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "tickets")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_item_id",nullable = false)
    private OrderItem orderItem;

    @CreatedDate
    @Column(name = "created_at",nullable = false ,updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;


    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_status",nullable = false)
    private TicketStatus ticketStatus;

    @Column(name = "validationCode", nullable = false, updatable = false,unique = true)
    private UUID validationCode;

    @Version
    private Long version;


}
