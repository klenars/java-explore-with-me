package ru.practicum.ewmserver.api.privatApi.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.compilation.CompilationDto;
import ru.practicum.ewmserver.api.privatApi.controller.admin.dtoRequest.NewCompilationDto;
import ru.practicum.ewmserver.service.admin.AdminCompilationsService;
import ru.practicum.ewmserver.entity.Compilation;

import javax.validation.Valid;

/**
 * API для работы с подборками событий {@link Compilation}, имеет поле:
 * {@link AdminCompilationsController#adminCompilationsService}
 */
@RestController
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
public class AdminCompilationsController {

    /**Сервис обработки запросов по подборкам от админа*/
    private final AdminCompilationsService adminCompilationsService;

    /**
     * Добавление новой подборки
     * @param newCompilationDto {@link NewCompilationDto}
     * @return {@link CompilationDto}
     */
    @PostMapping
    public CompilationDto createCompilation(@RequestBody @Valid NewCompilationDto newCompilationDto) {
        return adminCompilationsService.createCompilation(newCompilationDto);
    }

    /**
     * Удаление подборки
     * @param compId id удаляемой подборки
     */
    @DeleteMapping("/{compId}")
    public void deleteCompilation(@PathVariable long compId) {
        adminCompilationsService.deleteCompilation(compId);
    }

    /**
     * Удалить событие из подборки
     * @param compId id подборки
     * @param eventId id удаляемого события
     */
    @DeleteMapping("/{compId}/events/{eventId}")
    public void deleteEventFromCompilation(
            @PathVariable long compId,
            @PathVariable long eventId
    ) {
        adminCompilationsService.deleteEventFromCompilation(compId, eventId);
    }

    /**
     * Добавить событие в подборку
     * @param compId id подборки
     * @param eventId id события
     */
    @PatchMapping("/{compId}/events/{eventId}")
    public void addEventToCompilation(
            @PathVariable long compId,
            @PathVariable long eventId
    ) {
        adminCompilationsService.addEventToCompilation(compId, eventId);
    }

    /**
     * Открепить подборку на главной странице
     * @param compId id подборки
     */
    @DeleteMapping("/{compId}/pin")
    public void unpinCompilation(@PathVariable long compId) {
        adminCompilationsService.unpinCompilation(compId);
    }

    /**
     * Закрепить подборку на главной странице
     * @param compId id подборки
     */
    @PatchMapping("/{compId}/pin")
    public void pinCompilation(@PathVariable long compId) {
        adminCompilationsService.pinCompilation(compId);
    }
}
