package ru.practicum.ewmserver.error;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message, new Throwable("The required object was not found."));
    }
}
