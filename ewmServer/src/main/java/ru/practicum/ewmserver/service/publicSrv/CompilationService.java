package ru.practicum.ewmserver.service.publicSrv;

import ru.practicum.ewmserver.dto.compilation.CompilationDto;

import java.util.List;

/**
 * Интерфейс публичного сервиса подборок
 */
public interface CompilationService {

    /**
     * Получить подборку по id
     * @param compId id подборки
     * @return {@link CompilationDto}
     */
    CompilationDto getById(long compId);

    /**
     * Получение подборок событий
     * @param pinned Закрепленные или нет подборки
     * @param from номер первого элемента
     * @param size размер страницы
     * @return List of {@link CompilationDto}
     */
    List<CompilationDto> getAll(Boolean pinned, int from, int size);
}
