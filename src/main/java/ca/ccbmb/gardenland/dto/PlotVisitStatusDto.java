package ca.ccbmb.gardenland.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.NonNull;
import lombok.Value;

@Value
public class PlotVisitStatusDto {
    public static final PlotVisitStatusDto APPROVED = new PlotVisitStatusDto("APPROVED");
    public static final PlotVisitStatusDto CANCELLED = new PlotVisitStatusDto("CANCELLED");
    public static final PlotVisitStatusDto PENDING = new PlotVisitStatusDto("PENDING");

    private final String value;

    @JsonCreator
    public PlotVisitStatusDto(@NonNull String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
