package ru.practicum.ewmserver.error;

/**
 * Ошибка Не выполнения условий для совершения операции.
 */
public class ForbiddenError extends RuntimeException {
    public ForbiddenError(String message) {
        super(message);
    }
}
