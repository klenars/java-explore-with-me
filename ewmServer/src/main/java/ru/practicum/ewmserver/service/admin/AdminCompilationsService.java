package ru.practicum.ewmserver.service.admin;

import ru.practicum.ewmserver.dto.compilation.CompilationDto;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.NewCompilationDto;

/**
 * Интерфейс сервиса обработки запросов по подборкам от админа
 */
public interface AdminCompilationsService {

    /**
     * Создание подборки
     * @param newCompilationDto {@link NewCompilationDto}
     * @return {@link CompilationDto}
     */
    CompilationDto createCompilation(NewCompilationDto newCompilationDto);

    /**
     * Удаление подборки
     * @param compId id подборки
     */
    void deleteCompilation(long compId);

    /**
     * Удаление события из подборки
     * @param compId id подборки
     * @param eventId id события
     */
    void deleteEventFromCompilation(long compId, long eventId);

    /**
     * Добавление события в подборку
     * @param compId id подборки
     * @param eventId id события
     */
    void addEventToCompilation(long compId, long eventId);

    /**
     * Открепить подборку с главной страницы
     * @param compId id подборки
     */
    void unpinCompilation(long compId);

    /**
     * Закрепить подборку на главной странице
     * @param compId id подборки
     */
    void pinCompilation(long compId);
}
