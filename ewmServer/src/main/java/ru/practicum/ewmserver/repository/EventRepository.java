package ru.practicum.ewmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewmserver.entity.Event;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

    default Event getEventById(long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Event id=%d not found!", id))
        );
    }

    @Query("select e from events e where e.id in ?1")
    List<Event> getEventsByIds(Collection<Long> ids);



    @Query("select (count(e) > 0) from events e where e.category.id = ?1")
    boolean areEventsWithCategory(Long catId);
}
