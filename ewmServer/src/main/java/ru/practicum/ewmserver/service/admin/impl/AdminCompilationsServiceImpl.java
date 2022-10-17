package ru.practicum.ewmserver.service.admin.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewmserver.dto.compilation.CompilationDto;
import ru.practicum.ewmserver.dto.compilation.NewCompilationDto;
import ru.practicum.ewmserver.entity.Compilation;
import ru.practicum.ewmserver.entity.Event;
import ru.practicum.ewmserver.mapper.CompilationMapper;
import ru.practicum.ewmserver.repository.CompilationRepository;
import ru.practicum.ewmserver.repository.EventRepository;
import ru.practicum.ewmserver.service.admin.AdminCompilationsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCompilationsServiceImpl implements AdminCompilationsService {

    private final CompilationRepository compilationRepository;
    private final CompilationMapper compilationMapper;
    private final EventRepository eventRepository;

    @Override
    public CompilationDto createCompilation(NewCompilationDto newCompilationDto) {
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
}
