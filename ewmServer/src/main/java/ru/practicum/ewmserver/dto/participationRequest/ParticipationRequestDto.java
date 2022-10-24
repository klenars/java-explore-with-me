package ru.practicum.ewmserver.dto.participationRequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewmserver.entity.RequestStatus;

import java.time.LocalDateTime;

/**
 * Класс ДТО Заявка на участие в событии, содержит поля:
 * {@link ParticipationRequestDto#id},
 * {@link ParticipationRequestDto#event},
 * {@link ParticipationRequestDto#created},
 * {@link ParticipationRequestDto#requester},
 * {@link ParticipationRequestDto#status},
 */
@Getter
@Setter
public class ParticipationRequestDto {
    /**Идентификатор заявки*/
    private long id;
    /**Идентификатор события*/
    private long event;
    /**Дата и время создания заявки*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    /**Идентификатор пользователя, отправившего заявку*/
    private long requester;
    /**Статус заявки*/
    private RequestStatus status;
}
