package ru.practicum.ewmserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewmserver.entity.Event;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;

/**
 * Интерфейс репозитория событий {@link Event}
 */
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

    /**
     * Получение события по id
     * @param id id события
     * @return {@link Event}
     */
    default Event getById(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Event id=%d not found!", id))
        );
    }

    /**
     * Получение списка событий по списку id
     * @param ids список id сбытий
     * @return List of {@link Event}
     */
    @Query("select e from events e where e.id in ?1")
    List<Event> getEventsByIds(Collection<Long> ids);

    /**
     * Получение списка событий по id инициатора
     * @param id id инициатора
     * @param pageable {@link Pageable}
     * @return Page of {@link Event}
     */
    @Query("select e from events e where e.initiator.id = ?1")
    Page<Event> findByInitiatorId(Long id, Pageable pageable);

    /**
     * Есть ли сбытия с указанной категорией
     * @param catId id категории
     * @return boolean
     */
    @Query("select (count(e) > 0) from events e where e.category.id = ?1")
    boolean areEventsWithCategory(Long catId);

    @Query("select avg(e.rating) from events e where e.initiator.id = ?1 and e.rating is not null")
    double avgRatingByInitiatorId(Long id);
}
