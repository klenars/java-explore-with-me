package ru.practicum.ewmserver.mapper;

import org.mapstruct.Mapper;
import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.entity.Category;

@Mapper
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDto);
}
