package ru.practicum.ewmserver.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.dto.category.NewCategoryDto;
import ru.practicum.ewmserver.service.AdminCategoriesService;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoriesController {

    private final AdminCategoriesService adminCategoriesService;

    @PatchMapping
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto) {
        return adminCategoriesService.updateCategory(categoryDto);
    }

    @PostMapping
    public CategoryDto createCategory(@RequestBody NewCategoryDto newCategoryDto) {
        return adminCategoriesService.createCategory(newCategoryDto);
    }

    @DeleteMapping("/{catId}")
    public void deleteCategory(@PathVariable long catId) {
        adminCategoriesService.deleteCategory(catId);
    }
}
