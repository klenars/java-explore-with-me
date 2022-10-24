package ru.practicum.ewmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewmserver.entity.Category;

import javax.persistence.EntityNotFoundException;

/**
 * Интерфейс репозиоря для сохранения {@link Category}
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Получение Category по id
     * @param id must not be {@literal null}.
     * @return {@link Category}
     */
    default Category getById(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Category id=%d not found!", id))
        );
    }
}
