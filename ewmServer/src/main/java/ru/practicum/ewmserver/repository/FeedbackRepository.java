package ru.practicum.ewmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewmserver.entity.Feedback;
import ru.practicum.ewmserver.entity.FeedbackStatus;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    default Feedback getById(long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Event id=%d not found!", id))
        );
    }

    @Query("select f from Feedback f where f.status = ?1")
    List<Feedback> findByStatus(FeedbackStatus status);

    @Query("select avg(f.score) from Feedback f where f.event.id = ?1 and f.status <> 'REJECTED'")
    Double avgScoreByEventId(Long id);

    @Query("select (count(f) > 0) from Feedback f where f.event.id = ?1 and f.user.id = ?2")
    boolean existsByEventIdAndUserId(Long eventId, Long userId);
}
