package ru.practicum.ewmserver.error;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

/**
 * Обработчик ошибок
 */
@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    /**
     * обработка ConstraintViolationException
     * @param exception {@link ConstraintViolationException}
     * @return {@link ApiError}
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleConflictError(final ConstraintViolationException exception) {
        return new ApiError(exception, "Запрос приводит к нарушению целостности данных.", HttpStatus.CONFLICT);
    }

    /**
     * обработка EntityNotFoundException
     * @param exception {@link EntityNotFoundException}
     * @return {@link ApiError}
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleEntityNotFoundException(final EntityNotFoundException exception) {
        return new ApiError(exception, "Требуемый объект не найден.", HttpStatus.NOT_FOUND);
    }

    /**
     * обработка IllegalArgumentException
     * @param exception {@link IllegalArgumentException}
     * @return {@link ApiError}
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleIllegalArgumentException(final IllegalArgumentException exception) {
        return new ApiError(exception, "Ошибка запроса", HttpStatus.BAD_REQUEST);
    }

    /**
     * обработка javax.validation.ConstraintViolationException
     * @param exception {@link javax.validation.ConstraintViolationException}
     * @return {@link ApiError}
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleConstraintViolationException(final javax.validation.ConstraintViolationException exception) {
        return new ApiError(exception, "Ошибка запроса", HttpStatus.BAD_REQUEST);
    }

    /**
     * обработка ConflictError
     * @param exception {@link ConflictError}
     * @return {@link ApiError}
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleConflictError(final ConflictError exception) {
        return new ApiError(exception, "Запрос приводит к нарушению целостности данных.", HttpStatus.CONFLICT);
    }

    /**
     * обработка ForbiddenError
     * @param exception {@link ForbiddenError}
     * @return {@link ApiError}
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError handleForbiddenError(final ForbiddenError exception) {
        return new ApiError(exception, "Не выполнены условия для совершения операции.", HttpStatus.FORBIDDEN);
    }
}
