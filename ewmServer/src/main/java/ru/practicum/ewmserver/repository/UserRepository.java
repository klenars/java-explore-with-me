package ru.practicum.ewmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewmserver.entity.User;
import ru.practicum.ewmserver.error.EntityNotFoundException;


public interface UserRepository extends JpaRepository<User, Long> {
    default User getById(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("User id=%d not found!", id))
        );
    }
}
