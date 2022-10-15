package ru.practicum.ewmserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.dto.category.NewCategoryDto;
import ru.practicum.ewmserver.entity.Category;

@Mapper
public interface CategoryMapper {

    @Named("toDto")
    CategoryDto toDto(Category category);

    @Named("toEntity")
    Category toEntity(CategoryDto categoryDto);

    Category newToEntity(NewCategoryDto newCategoryDto);
}
