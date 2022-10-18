package ru.practicum.ewmserver.service.publicSrv;

import ru.practicum.ewmserver.dto.compilation.CompilationDto;

import java.util.List;

public interface CompilationService {
    CompilationDto getById(long compId);

    List<CompilationDto> getAll(Boolean pinned, int from, int size);
}
