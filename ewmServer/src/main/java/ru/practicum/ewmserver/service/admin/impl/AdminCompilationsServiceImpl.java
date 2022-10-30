package ru.practicum.ewmserver.service.admin.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewmserver.dto.compilation.CompilationDto;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.NewCompilationDto;
import ru.practicum.ewmserver.entity.Compilation;
import ru.practicum.ewmserver.entity.Event;
import ru.practicum.ewmserver.mapper.CompilationMapper;
import ru.practicum.ewmserver.repository.CompilationRepository;
import ru.practicum.ewmserver.repository.EventRepository;
import ru.practicum.ewmserver.service.admin.AdminCompilationsService;

import java.util.List;

/**
 * Реализация интерфейса {@link AdminCompilationsService}, содержит поля:
 * {@link AdminCompilationsServiceImpl#compilationRepository},
 * {@link AdminCompilationsServiceImpl#compilationMapper},
 * {@link AdminCompilationsServiceImpl#eventRepository}
 */
@Service
@RequiredArgsConstructor
public class AdminCompilationsServiceImpl implements AdminCompilationsService {

    /**Репозиторий подборок {@link CompilationRepository}*/
    private final CompilationRepository compilationRepository;

    /**Маппер подборок {@link CompilationMapper}*/
    private final CompilationMapper compilationMapper;

    /**Репозиторий событий {@link EventRepository}*/
    private final EventRepository eventRepository;

    @Override
    public CompilationDto createCompilation(@NonNull NewCompilationDto newCompilationDto) {
        Compilation compilation = compilationMapper.toEntity(newCompilationDto);
        List<Event> events = eventRepository.getEventsByIds(newCompilationDto.getEvents());
        compilation.setEvents(events);

        return compilationMapper.toDto(compilationRepository.save(compilation));
    }

    @Override
    public void deleteCompilation(long compId) {
        Compilation compilation = compilationRepository.getById(compId);
        compilationRepository.delete(compilation);
    }

    @Override
    @Transactional
    public void deleteEventFromCompilation(long compId, long eventId) {
        Compilation compilation = compilationRepository.getById(compId);
        Event event = eventRepository.getById(eventId);

        compilation.getEvents().remove(event);
    }

    @Override
    @Transactional
    public void addEventToCompilation(long compId, long eventId) {
        Compilation compilation = compilationRepository.getById(compId);
        Event event = eventRepository.getById(eventId);

        compilation.getEvents().add(event);
    }

    @Override
    @Transactional
    public void unpinCompilation(long compId) {
        Compilation compilation = compilationRepository.getById(compId);
        compilation.setPinned(false);
    }

    @Override
    @Transactional
    public void pinCompilation(long compId) {
        Compilation compilation = compilationRepository.getById(compId);
        compilation.setPinned(true);
    }
}
