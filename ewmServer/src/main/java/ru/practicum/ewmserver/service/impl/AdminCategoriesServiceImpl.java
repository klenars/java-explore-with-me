package ru.practicum.ewmserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.dto.category.NewCategoryDto;
import ru.practicum.ewmserver.entity.Category;
import ru.practicum.ewmserver.mapper.CategoryMapper;
import ru.practicum.ewmserver.repository.CategoryRepository;
import ru.practicum.ewmserver.service.AdminCategoriesService;

@Service
@RequiredArgsConstructor
public class AdminCategoriesServiceImpl implements AdminCategoriesService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

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
        categoryRepository.delete(category);
    }
}
