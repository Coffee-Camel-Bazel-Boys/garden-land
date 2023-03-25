package ca.ccbmb.gardenland.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.NonNull;
import lombok.Value;

@Value
public class PlotSizeUnitTypeDto {
    public static final PlotSizeUnitTypeDto SQUARE_FEET = new PlotSizeUnitTypeDto("SQUARE_FEET");

    private final String value;

    @JsonCreator
    public PlotSizeUnitTypeDto(@NonNull String value) {
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