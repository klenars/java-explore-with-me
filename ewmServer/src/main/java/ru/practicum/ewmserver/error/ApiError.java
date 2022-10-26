package ru.practicum.ewmserver.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс информации об ошибке, содержит поля:
 * {@link ApiError#message},
 * {@link ApiError#reason},
 * {@link ApiError#status},
 * {@link ApiError#timestamp},
 * {@link ApiError#errors},
 */
@Getter
public class ApiError {

    /**Сообщение ошибки*/
    private final String message;
    /**Общая причина*/
    private final String reason;
    /**HTTP статус*/
    private final HttpStatus status;
    /**Дата и время возникновения*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;
    /**Стек-трейс ошибок*/
    private final List<StackTraceElement> errors;

    public ApiError(RuntimeException exception, String reason, HttpStatus status) {
        this.message = exception.getMessage();
        this.reason = reason;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.errors = List.of(exception.getStackTrace());
    }
}