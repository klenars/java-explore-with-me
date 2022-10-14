package ru.practicum.ewmserver.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.category.CategoryDto;
import ru.practicum.ewmserver.dto.category.NewCategoryDto;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class adminCategoriesController {
    //TODO

    @PatchMapping
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto) {
        //TODO
        return null;
    }

    @PostMapping
    public CategoryDto updateCategory(@RequestBody NewCategoryDto newCategoryDto) {
        //TODO
        return null;
    }

    @DeleteMapping("/{catId}")
    public void deleteCategory(@PathVariable long catId) {
        //TODO
    }
}
