package ru.practicum.ewmserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.NewCategoryDto;
import ru.practicum.ewmserver.entity.Category;

/**
 * Маппер классов {@link Category}, {@link CategoryDto}, {@link NewCategoryDto}
 */
@Mapper
public interface CategoryMapper {

    /**
     * Маппер Ентити в ДТО
     * @param category {@link Category}
     * @return {@link CategoryDto}
     */
    @Named("toDto")
    CategoryDto toDto(Category category);

    /**
     * Мапер ДТО в Ентити
     * @param categoryDto {@link CategoryDto}
     * @return {@link Category}
     */
    @Named("toEntity")
    Category toEntity(CategoryDto categoryDto);

    /**
     * Маппер NewCategoryDto в Category
     * @param newCategoryDto {@link NewCategoryDto}
     * @return {@link Category}
     */
    Category newToEntity(NewCategoryDto newCategoryDto);
}
