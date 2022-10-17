package ru.practicum.ewmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewmserver.entity.Event;

import javax.persistence.EntityNotFoundException;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

    default Event getById(long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Event id=%d not found!", id))
        );
    }

    @Query("select (count(e) > 0) from events e where e.category.id = ?1")
    boolean areEventsWithCategory(Long catId);
}
