package ru.practicum.ewmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewmserver.entity.ParticipationRequest;
import ru.practicum.ewmserver.entity.RequestStatus;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;

/**
 * Интерфейс репозитория Запросов на участие в событиях {@link ParticipationRequest}
 */
public interface RequestRepository extends JpaRepository<ParticipationRequest, Long> {

    /**
     * Получение запроса по id
     * @param id must not be {@literal null}.
     * @return {@link ParticipationRequest}
     */
    default ParticipationRequest getById(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Request id=%d not found!", id))
        );
    }

    /**
     * Плучение запросов по id события
     * @param id id события
     * @return List of {@link ParticipationRequest}
     */
    @Query("select r from requests r where r.event.id = ?1")
    List<ParticipationRequest> getAllByEventId(Long id);

    /**
     * Плучение запросов по id события и статусу
     * @param id id события
     * @param status {@link RequestStatus}
     * @return List of {@link ParticipationRequest}
     */
    @Query("select r from requests r where r.event.id = ?1 and r.status = ?2")
    List<ParticipationRequest> getAllByEventIdAndStatus(Long id, RequestStatus status);

    /**
     * Получение всех запросов юзера по id
     * @param id id юзера
     * @return List of {@link ParticipationRequest}
     */
    @Query("select r from requests r where r.requester.id = ?1")
    List<ParticipationRequest> getAllByUserId(Long id);

    /**
     * Проверка существует ли уже запрос на событие от этого юзера
     * @param eventId id события
     * @param userId id юзера
     * @return boolean
     */
    @Query("select (count(r) > 0) from requests r where r.event.id = ?1 and r.requester.id = ?2")
    boolean requestAlreadyExist(Long eventId, Long userId);

    /**
     * Проверка существует ли запрос с определенным статусом на событие от этого юзера
     * @param eventId id события
     * @param userId id юзера
     * @param status {@link RequestStatus}
     * @return boolean
     */
    @Query("select (count(r) > 0) from requests r where r.event.id = ?1 and r.requester.id = ?2 and r.status = ?3")
    boolean existsByEventIdAndRequesterIdAndStatus(Long eventId, Long userId, RequestStatus status);

    /**
     * Колличество запросов по id события c определенным статусом
     * @param id id события
     * @param statuses {@link RequestStatus}
     * @return long
     */
    @Query("select count(r) from requests r where r.event.id = ?1 and r.status in ?2")
    long quantityEventRequests(Long id, Collection<RequestStatus> statuses);


}
