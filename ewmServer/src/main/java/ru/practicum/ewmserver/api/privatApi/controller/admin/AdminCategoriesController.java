package ru.practicum.ewmserver.api.privatApi.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.NewCategoryDto;
import ru.practicum.ewmserver.service.admin.AdminCategoriesService;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoriesController {

    private final AdminCategoriesService adminCategoriesService;

    @PatchMapping
    public CategoryDto updateCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return adminCategoriesService.updateCategory(categoryDto);
    }

    @PostMapping
    public CategoryDto createCategory(@RequestBody @Valid NewCategoryDto newCategoryDto) {
        return adminCategoriesService.createCategory(newCategoryDto);
    }

    @DeleteMapping("/{catId}")
    public void deleteCategory(@PathVariable long catId) {
        adminCategoriesService.deleteCategory(catId);
    }
}
