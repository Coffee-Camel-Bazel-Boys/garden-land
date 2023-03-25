package ca.ccbmb.gardenland.controller;

import ca.ccbmb.gardenland.core.user.UserService;
import ca.ccbmb.gardenland.dto.LoginDto;
import ca.ccbmb.gardenland.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/{userNumber}")
    public UserDto get(@PathVariable String userNumber) {
        return service.get(userNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto userDto) {
        return service.create(userDto);
    }

    @PutMapping("/{userNumber}")
    public UserDto update(@PathVariable String userNumber, @RequestBody UserDto userDto) {
        userDto.setUserNumber(userNumber);
        return service.update(userDto);
    }

    @PutMapping()
    public UserDto login(@RequestBody LoginDto loginDto) {
        //TODO: Implement this
        return null;
    }

    @DeleteMapping("/{userNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String userNumber) {
        service.delete(userNumber);
    }
}
