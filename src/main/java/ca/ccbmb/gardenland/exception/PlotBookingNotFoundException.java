package ca.ccbmb.gardenland.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlotBookingNotFoundException extends RuntimeException {
    public PlotBookingNotFoundException(String plotBookingNumber) {
        super(String.format("a booking with the number %s could not be found", plotBookingNumber));
    }
}
