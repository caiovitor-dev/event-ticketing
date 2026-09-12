package dev.caiovitor.eventticketing.repository;

import dev.caiovitor.eventticketing.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
