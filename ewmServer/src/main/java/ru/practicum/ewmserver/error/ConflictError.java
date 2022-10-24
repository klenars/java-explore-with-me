package ru.practicum.ewmserver.error;

/**
 * Ошибка нарушения целостности данных.
 */
public class ConflictError extends RuntimeException {
    public ConflictError(String message) {
        super(message);
    }
}
