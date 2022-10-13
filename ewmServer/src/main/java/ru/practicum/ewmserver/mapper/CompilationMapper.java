package ru.practicum.ewmserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.ewmserver.dto.compilation.CompilationDto;
import ru.practicum.ewmserver.dto.compilation.NewCompilationDto;
import ru.practicum.ewmserver.entity.Compilation;

@Mapper(uses = EventMapper.class)
public interface CompilationMapper {

    @Mapping(target = "events", qualifiedByName = "toShortDto")
    CompilationDto toDto(Compilation compilation);

    Compilation toEntity(NewCompilationDto newCompilationDto);
}
