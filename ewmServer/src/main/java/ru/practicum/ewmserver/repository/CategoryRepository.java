package ru.practicum.ewmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewmserver.entity.Category;
import ru.practicum.ewmserver.error.EntityNotFoundException;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    default Category getById(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Category id=%d not found!", id))
        );
    }
}
