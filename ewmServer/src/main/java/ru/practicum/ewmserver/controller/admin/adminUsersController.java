package ru.practicum.ewmserver.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.admin.NewUserRequest;
import ru.practicum.ewmserver.dto.user.UserDto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Validated
public class adminUsersController {
    //TODO

    @GetMapping
    public List<UserDto> getAll(
            @RequestParam List<Long> ids,
            @PositiveOrZero @RequestParam(defaultValue = "0") int from,
            @Positive @RequestParam(defaultValue = "10") int size
    ) {
        //TODO
        return null;
    }

    @PostMapping
    public UserDto createUser(@RequestBody NewUserRequest newUserRequest) {
        //TODO
        return null;
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        //TODO
    }
}
