package ru.practicum.ewmserver.service.publicSrv;

import ru.practicum.ewmserver.dto.category.CategoryDto;

import java.util.List;

/**
 * Интерфейс публичного сервиса категорий
 */
public interface CategoryService {

    /**
     * Получить категории
     * @param from номер первого элемента
     * @param size размер страницы
     * @return List of {@link CategoryDto}
     */
    List<CategoryDto> getAll(int from, int size);

    /**
     * Полученние категории по id
     * @param catId id категории
     * @return {@link CategoryDto}
     */
    CategoryDto getById(long catId);
}
