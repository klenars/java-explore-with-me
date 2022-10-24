package ru.practicum.ewmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewmserver.entity.Location;

/**
 * Интерфейс репозитория локаций {@link Location}
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
}
