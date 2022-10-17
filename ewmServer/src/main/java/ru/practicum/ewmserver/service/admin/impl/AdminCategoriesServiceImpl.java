package ru.practicum.ewmserver.service.admin.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.dto.category.NewCategoryDto;
import ru.practicum.ewmserver.entity.Category;
import ru.practicum.ewmserver.error.ConflictError;
import ru.practicum.ewmserver.mapper.CategoryMapper;
import ru.practicum.ewmserver.repository.CategoryRepository;
import ru.practicum.ewmserver.repository.EventRepository;
import ru.practicum.ewmserver.service.admin.AdminCategoriesService;

@Service
@RequiredArgsConstructor
public class AdminCategoriesServiceImpl implements AdminCategoriesService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final EventRepository eventRepository;

    @Override
    public CategoryDto createCategory(NewCategoryDto newCategoryDto) {
        Category newCategory = categoryMapper.newToEntity(newCategoryDto);

        return categoryMapper.toDto(categoryRepository.save(newCategory));
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(CategoryDto categoryDto) {
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

    private void checkCategoryEvents(long catId) {
        if (eventRepository.areEventsWithCategory(catId)) {
            throw new ConflictError(String.format("У категории id=%d есть связанные события!", catId));
        }
    }
}
