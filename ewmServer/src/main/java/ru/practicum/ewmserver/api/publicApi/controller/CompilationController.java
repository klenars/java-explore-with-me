package ru.practicum.ewmserver.api.publicApi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.compilation.CompilationDto;
import ru.practicum.ewmserver.service.publicSrv.CompilationService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * Публичный API для работы с подборками событий, имеет поле:
 * {@link CompilationService}
 */
@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
@Validated
public class CompilationController {

    /**Сервис обработки запросов по подборкам событий*/
    private final CompilationService compilationService;

    /**
     * Получение подборок событий
     * @param pinned искать только закрепленные/не закрепленные подборки
     * @param from количество элементов, которые нужно пропустить для формирования текущего набора
     * @param size количество элементов в наборе
     * @return List of {@link CompilationDto}
     */
    @GetMapping
    public List<CompilationDto> getAll(
            @RequestParam(required = false) Boolean pinned,
            @PositiveOrZero @RequestParam(defaultValue = "0") int from,
            @Positive @RequestParam(defaultValue = "10") int size
    ) {
        return compilationService.getAll(pinned, from, size);
    }

    /**
     * Получение подборки событий по его id
     * @param compId id категории
     * @return {@link CompilationDto}
     */
    @GetMapping("/{compId}")
    public CompilationDto getById(
            @PathVariable long compId
    ) {
        return compilationService.getById(compId);
    }
}
