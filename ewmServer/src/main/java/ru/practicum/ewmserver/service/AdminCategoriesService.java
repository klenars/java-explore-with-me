package ru.practicum.ewmserver.service;

import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.dto.category.NewCategoryDto;

public interface AdminCategoriesService {

    CategoryDto createCategory(NewCategoryDto newCategoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto);

    void deleteCategory(long catId);
}
