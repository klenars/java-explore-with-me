package ru.practicum.ewmserver.service.admin.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.NewCategoryDto;
import ru.practicum.ewmserver.entity.Category;
import ru.practicum.ewmserver.error.ConflictError;
import ru.practicum.ewmserver.mapper.CategoryMapper;
import ru.practicum.ewmserver.repository.CategoryRepository;
import ru.practicum.ewmserver.repository.EventRepository;
import ru.practicum.ewmserver.service.admin.AdminCategoriesService;

/**
 * Реализация интерфейса {@link AdminCategoriesService}, содержит поля:
 * {@link AdminCategoriesServiceImpl#categoryRepository},
 * {@link AdminCategoriesServiceImpl#categoryMapper},
 * {@link AdminCategoriesServiceImpl#eventRepository},
 */
@Service
@RequiredArgsConstructor
public class AdminCategoriesServiceImpl implements AdminCategoriesService {

    /**Репозиторий Категорий {@link CategoryRepository}*/
    private final CategoryRepository categoryRepository;

    /**Маппер Категорий {@link CategoryMapper}*/
    private final CategoryMapper categoryMapper;

    /**Репозиторий Событий {@link EventRepository}*/
    private final EventRepository eventRepository;

    @Override
    public CategoryDto createCategory(@NonNull NewCategoryDto newCategoryDto) {
        Category newCategory = categoryMapper.newToEntity(newCategoryDto);

        return categoryMapper.toDto(categoryRepository.save(newCategory));
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(@NonNull CategoryDto categoryDto) {
        Category category = categoryRepository.getById(categoryDto.getId());
        category.setName(categoryDto.getName());

        return categoryMapper.toDto(category);
    }

    @Override
    public void deleteCategory(long catId) {
        Category category = categoryRepository.getById(catId);
        checkCategoryEvents(catId);
        categoryRepository.delete(category);
    }

    /**
     * Проверка категории перед удалением на наличие связанных событий
     * @param catId id категории
     * @throws ConflictError
     */
    private void checkCategoryEvents(long catId) {
        if (eventRepository.areEventsWithCategory(catId)) {
            throw new ConflictError(String.format("У категории id=%d есть связанные события!", catId));
        }
    }
}
