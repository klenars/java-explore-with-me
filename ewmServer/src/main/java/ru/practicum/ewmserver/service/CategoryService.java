package ru.practicum.ewmserver.service;

import ru.practicum.ewmserver.dto.category.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll(int from, int size);

    CategoryDto getById(long catId);
}
