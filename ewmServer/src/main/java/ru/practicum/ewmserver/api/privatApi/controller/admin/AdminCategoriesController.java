package ru.practicum.ewmserver.api.privatApi.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.NewCategoryDto;
import ru.practicum.ewmserver.service.admin.AdminCategoriesService;
import ru.practicum.ewmserver.entity.Category;

import javax.validation.Valid;

/**
 * Контроллер категорий {@link Category} для запросов от администратора, содержит поле:
 * {@link AdminCategoriesService}
 */
@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoriesController {

    /**Сервис обработки запросов по категориям от админа*/
    private final AdminCategoriesService adminCategoriesService;

    /**
     * Изменение категории
     * @param categoryDto {@link CategoryDto}
     * @return {@link CategoryDto}
     */
    @PatchMapping
    public CategoryDto updateCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return adminCategoriesService.updateCategory(categoryDto);
    }

    /**
     * Добавление новой категории
     * @param newCategoryDto {@link NewCategoryDto}
     * @return {@link CategoryDto}
     */
    @PostMapping
    public CategoryDto createCategory(@RequestBody @Valid NewCategoryDto newCategoryDto) {
        return adminCategoriesService.createCategory(newCategoryDto);
    }

    /**
     *Удаление категории
     * @param catId id удаляемой категории
     */
    @DeleteMapping("/{catId}")
    public void deleteCategory(@PathVariable long catId) {
        adminCategoriesService.deleteCategory(catId);
    }
}
