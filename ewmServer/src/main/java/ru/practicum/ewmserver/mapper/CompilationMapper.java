package ru.practicum.ewmserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.ewmserver.dto.compilation.CompilationDto;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.NewCompilationDto;
import ru.practicum.ewmserver.entity.Compilation;

/**
 * Маппер Подборок
 */
@Mapper(uses = EventMapper.class)
public interface CompilationMapper {

    /**
     * Маппер Подборки в ДТО
     * @param compilation {@link Compilation}
     * @return {@link CompilationDto}
     */
    @Mapping(target = "events", qualifiedByName = "toShortDto")
    CompilationDto toDto(Compilation compilation);

    /**
     * Маппер NewCompilationDto в Compilation
     * @param newCompilationDto {@link NewCompilationDto}
     * @return {@link Compilation}
     */
    @Mapping(target = "events", ignore = true)
    Compilation toEntity(NewCompilationDto newCompilationDto);
}
