package ru.practicum.ewmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewmserver.entity.Feedback;

import javax.persistence.EntityNotFoundException;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    default Feedback getById(long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Event id=%d not found!", id))
        );
    }

    @Query("select avg(f.score) from Feedback f where f.event.id = ?1")
    Double avgScoreByEventId(Long id);

    @Query("select (count(f) > 0) from Feedback f where f.event.id = ?1 and f.user.id = ?2")
    boolean existsByEventIdAndUserId(Long eventId, Long userId);
}
