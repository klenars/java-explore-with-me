package ru.practicum.ewmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewmserver.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
