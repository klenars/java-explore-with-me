package ru.practicum.ewmserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.compilation.CompilationDto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
@Validated
public class CompilationController {

    @GetMapping
    public List<CompilationDto> getAll(
            @RequestParam(required = false) boolean pinned,
            @PositiveOrZero @RequestParam(defaultValue = "0") int from,
            @Positive @RequestParam(defaultValue = "10") int size
    ) {
        return null;
    }

    @GetMapping("/{compId}")
    public CompilationDto getById(
            @PathVariable long compId
    ) {
        return null;
    }
}
