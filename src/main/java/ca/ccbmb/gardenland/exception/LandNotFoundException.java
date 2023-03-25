package ca.ccbmb.gardenland.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LandNotFoundException extends RuntimeException {
    public LandNotFoundException(String landNumber) {
        super(String.format("land with the land number %s could not be found", landNumber));
    }
}
