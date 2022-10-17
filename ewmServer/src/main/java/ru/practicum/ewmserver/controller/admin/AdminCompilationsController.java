package ru.practicum.ewmserver.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.compilation.CompilationDto;
import ru.practicum.ewmserver.dto.compilation.NewCompilationDto;
import ru.practicum.ewmserver.service.admin.AdminCompilationsService;

@RestController
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
public class AdminCompilationsController {

    private final AdminCompilationsService adminCompilationsService;

    @PostMapping
    public CompilationDto createCompilation(@RequestBody NewCompilationDto newCompilationDto) {
        return adminCompilationsService.createCompilation(newCompilationDto);
    }

    @DeleteMapping("/{compId}")
    public void deleteCompilation(@PathVariable long compId) {
        adminCompilationsService.deleteCompilation(compId);
    }

    @DeleteMapping("/{compId}/events/{eventId}")
    public void deleteEventFromCompilation(
            @PathVariable long compId,
            @PathVariable long eventId
    ) {
        //TODO
    }

    @PatchMapping("/{compId}/events/{eventId}")
    public void addEventToCompilation(
            @PathVariable long compId,
            @PathVariable long eventId
    ) {
        //TODO
    }

    @DeleteMapping("/{compId}/pin")
    public void unpinCompilation(@PathVariable long compId) {
        //TODO
    }

    @PatchMapping("/{compId}/pin")
    public void pinCompilation(@PathVariable long compId) {
        //TODO
    }
}
