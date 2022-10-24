package ru.practicum.ewmserver.service.publicSrv.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.mapper.CategoryMapper;
import ru.practicum.ewmserver.repository.CategoryRepository;
import ru.practicum.ewmserver.service.publicSrv.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация интерфейса {@link CategoryService}, имеет поля:
 * {@link CategoryServiceImpl#categoryRepository},
 * {@link CategoryServiceImpl#categoryMapper}
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    /**Репозиторий Категорий {@link CategoryRepository}*/
    private final CategoryRepository categoryRepository;

    /**Маппер Категорий {@link CategoryMapper}*/
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAll(int from, int size) {
        return categoryRepository.findAll(PageRequest.of(from / size, size)).stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getById(long catId) {
        return categoryMapper.toDto(categoryRepository.getById(catId));
    }
}
