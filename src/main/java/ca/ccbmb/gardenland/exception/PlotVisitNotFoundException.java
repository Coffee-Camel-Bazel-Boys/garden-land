package ca.ccbmb.gardenland.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlotVisitNotFoundException extends RuntimeException {
    public PlotVisitNotFoundException(String plotNumber) {
        super(String.format("a plot with the plot number %s could not be found", plotNumber));
    }
}
