package ca.ccbmb.gardenland.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlotPictureNotFoundException extends RuntimeException {
    public PlotPictureNotFoundException(String plotPictureNumber) {
        super(String.format("a plot picture with the number %s could not be found", plotPictureNumber));
    }
}
