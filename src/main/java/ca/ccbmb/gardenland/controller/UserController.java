package ca.ccbmb.gardenland.controller;

import ca.ccbmb.gardenland.dto.LoginDto;
import ca.ccbmb.gardenland.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/{userNumber}")
    public UserDto get(@PathVariable String userNumber) {
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto userDto) {
        return null;
    }

    @PutMapping("/{userNumber}")
    public UserDto update(@PathVariable String userNumber, @RequestBody UserDto userDto) {
        userDto.setUserNumber(userNumber);
        return null;
    }

    @PutMapping()
    public UserDto login(@RequestBody LoginDto loginDto) {
        return null;
    }

    @DeleteMapping("/{userNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String userNumber) {

    }
}
