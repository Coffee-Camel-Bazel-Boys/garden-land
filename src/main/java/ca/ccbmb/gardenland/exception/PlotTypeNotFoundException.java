package ca.ccbmb.gardenland.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlotTypeNotFoundException extends RuntimeException {
    public PlotTypeNotFoundException(String plotTypeNumber) {
        super(String.format("a plot type with the number %s could not be found", plotTypeNumber));
    }
}
