package ru.practicum.ewmserver.service.publicSrv.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewmserver.dto.compilation.CompilationDto;
import ru.practicum.ewmserver.entity.Compilation;
import ru.practicum.ewmserver.mapper.CompilationMapper;
import ru.practicum.ewmserver.repository.CompilationRepository;
import ru.practicum.ewmserver.service.publicSrv.CompilationService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация интерфейса {@link CompilationService}, имеет поля:
 * {@link CompilationServiceImpl#compilationRepository},
 * {@link CompilationServiceImpl#compilationMapper},
 */
@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    /**Репозиторий подборок {@link CompilationRepository}*/
    private final CompilationRepository compilationRepository;

    /**Маппер подборок {@link CompilationMapper}*/
    private final CompilationMapper compilationMapper;

    @Override
    public CompilationDto getById(long compId) {
        return compilationMapper.toDto(compilationRepository.getById(compId));
    }

    @Override
    public List<CompilationDto> getAll(Boolean pinned, int from, int size) {
        Page<Compilation> compilations;
        if (pinned == null) {
            compilations = compilationRepository.findAll(PageRequest.of(from / size, size));
        } else {
            compilations = compilationRepository.findAllCompilationByPinning(
                    pinned,
                    PageRequest.of(from / size, size)
            );
        }
        return compilations.stream()
                .map(compilationMapper::toDto)
                .collect(Collectors.toList());
    }
}
