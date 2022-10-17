package ru.practicum.ewmserver.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmserver.dto.admin.NewUserRequest;
import ru.practicum.ewmserver.dto.user.UserDto;
import ru.practicum.ewmserver.service.admin.AdminUserService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Validated
public class AdminUsersController {

    private final AdminUserService adminUserService;

    @GetMapping
    public List<UserDto> getAll(
            @RequestParam List<Long> ids,
            @PositiveOrZero @RequestParam(defaultValue = "0") int from,
            @Positive @RequestParam(defaultValue = "10") int size
    ) {
        return adminUserService.getAll(ids, from, size);
    }

    @PostMapping
    public UserDto createUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        log.info("adminUsersController POST createUser got NewUserRequest: {}", newUserRequest);

        return adminUserService.createUser(newUserRequest);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        adminUserService.deleteUser(userId);
    }
}
