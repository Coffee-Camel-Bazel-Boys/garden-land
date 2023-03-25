package ca.ccbmb.gardenland.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userNumber) {
        super(String.format("a user with the user number %s could not be found", userNumber));
    }
}
