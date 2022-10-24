package ru.practicum.ewmserver.service.admin;

import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.NewCategoryDto;

public interface AdminCategoriesService {

    CategoryDto createCategory(NewCategoryDto newCategoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto);

    void deleteCategory(long catId);
}
