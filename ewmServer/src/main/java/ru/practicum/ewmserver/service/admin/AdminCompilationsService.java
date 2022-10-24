package ru.practicum.ewmserver.service.admin;

import ru.practicum.ewmserver.dto.compilation.CompilationDto;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.NewCompilationDto;

public interface AdminCompilationsService {
    CompilationDto createCompilation(NewCompilationDto newCompilationDto);

    void deleteCompilation(long compId);

    void deleteEventFromCompilation(long compId, long eventId);

    void addEventToCompilation(long compId, long eventId);

    void unpinCompilation(long compId);

    void pinCompilation(long compId);
}
