package ru.practicum.ewmserver.api.publicApi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.service.publicSrv.CategoryService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * Публичный API для работы с категориями, содержит поле:
 * {@link CategoryService}
 */
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
public class CategoryController {

    /**Поле сервиса обработки запросов по категориям публичного апи*/
    private final CategoryService categoryService;


    /**
     * Получение категорий
     * @param from количество категорий, которые нужно пропустить для формирования текущего набора
     * @param size количество категорий в наборе
     * @return List of {@link CategoryDto}
     */
    @GetMapping
    public List<CategoryDto> getAll(
            @PositiveOrZero @RequestParam(defaultValue = "0") int from,
            @Positive @RequestParam(defaultValue = "10") int size
    ) {
        return categoryService.getAll(from, size);
    }

    /**
     * Получение информации о категории по её идентификатору
     * @param catId id категории
     * @return {@link CategoryDto}
     */
    @GetMapping("/{catId}")
    public CategoryDto getById(@PathVariable long catId) {
        return categoryService.getById(catId);
    }
}
