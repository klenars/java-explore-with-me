package ru.practicum.ewmserver.dto.participationRequest;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewmserver.entity.RequestStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ParticipationRequestDto {
    private long id;
    private long event;
    private LocalDateTime created;
    private long requester;
    private RequestStatus status;
}
