package ru.practicum.ewmserver.service.admin;

import lombok.NonNull;
import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.NewCategoryDto;
import ru.practicum.ewmserver.entity.Category;

/**
 * Интерфейс сервиса обработки запросов по категориям от админа
 */
public interface AdminCategoriesService {

    /**
     * Создание категории {@link Category}
     * @param newCategoryDto {@link NewCategoryDto}
     * @return {@link CategoryDto}
     */
    CategoryDto createCategory(@NonNull NewCategoryDto newCategoryDto);

    /**
     * Оюновление категории
     * @param categoryDto {@link CategoryDto}
     * @return {@link CategoryDto}
     */
    CategoryDto updateCategory(@NonNull CategoryDto categoryDto);

    /**
     * Удаление категории
     * @param catId id категории
     */
    void deleteCategory(long catId);
}
