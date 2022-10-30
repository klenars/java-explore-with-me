package ru.practicum.ewmserver.dto.feedback;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewmserver.entity.FeedbackStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link ru.practicum.ewmserver.entity.Feedback} entity
 */
@Getter
@Setter
public class FeedbackDtoOut implements Serializable {
    private Long id;
    private String text;
    private Integer score;
    private Long eventId;
    private Long userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    private FeedbackStatus status;
}