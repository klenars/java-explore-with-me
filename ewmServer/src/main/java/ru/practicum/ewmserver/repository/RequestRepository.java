package ru.practicum.ewmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewmserver.entity.ParticipationRequest;
import ru.practicum.ewmserver.entity.RequestStatus;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;

public interface RequestRepository extends JpaRepository<ParticipationRequest, Long> {

    default ParticipationRequest getById(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Request id=%d not found!", id))
        );
    }

    @Query("select r from requests r where r.event.id = ?1")
    List<ParticipationRequest> getAllByEventId(Long id);

    @Query("select r from requests r where r.event.id = ?1 and r.status = ?2")
    List<ParticipationRequest> getAllByEventIdAndStatus(Long id, RequestStatus status);

    @Query("select r from requests r where r.requester.id = ?1")
    List<ParticipationRequest> getAllByUserId(Long id);

    @Query("select (count(r) > 0) from requests r where r.event.id = ?1 and r.requester.id = ?2")
    boolean requestAlreadyExist(Long eventId, Long userId);

    @Query("select count(r) from requests r where r.event.id = ?1 and r.status in ?2")
    long quantityEventRequests(Long id, Collection<RequestStatus> statuses);


}
