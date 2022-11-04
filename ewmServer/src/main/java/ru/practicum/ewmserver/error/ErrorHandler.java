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
     * Обработка ошибок целостности двнных
     * @param exception {@link RuntimeException}
     * @return {@link ApiError}
     */
    @ExceptionHandler({ConstraintViolationException.class, ConflictError.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleConflictError(final RuntimeException exception) {
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
     * обработка Ошибок запроса
     * @param exception {@link RuntimeException}
     * @return {@link ApiError}
     */
    @ExceptionHandler({IllegalArgumentException.class, javax.validation.ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleIllegalArgumentException(final RuntimeException exception) {
        return new ApiError(exception, "Ошибка запроса", HttpStatus.BAD_REQUEST);
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
