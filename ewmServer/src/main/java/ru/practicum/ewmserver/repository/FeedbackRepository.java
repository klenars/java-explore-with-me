package ru.practicum.ewmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewmserver.entity.Feedback;
import ru.practicum.ewmserver.entity.FeedbackStatus;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Интерфейс репозитория отзывов {@link Feedback}
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    /**
     * Плучить отзыв по id
     * @param id id отзыва
     * @return {@link Feedback}
     * @throws {@link EntityNotFoundException}
     */
    default Feedback getById(long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Event id=%d not found!", id))
        );
    }

    /**
     * Получить события по статусу
     * @param status {@link FeedbackStatus}
     * @return List of {@link Feedback}
     */
    @Query("select f from Feedback f where f.status = ?1")
    List<Feedback> findByStatus(FeedbackStatus status);

    /**
     * Получить среднюю оценку отзывов по id события
     * @param id id события
     * @return Double
     */
    @Query("select avg(f.score) from Feedback f where f.event.id = ?1 and f.status <> 'REJECTED'")
    Double avgScoreByEventId(Long id);

    /**
     * Проверка надичия отзыва по id юзера и id события
     * @param eventId id события
     * @param userId id юзера
     * @return boolean
     */
    @Query("select (count(f) > 0) from Feedback f where f.event.id = ?1 and f.user.id = ?2")
    boolean existsByEventIdAndUserId(Long eventId, Long userId);
}
