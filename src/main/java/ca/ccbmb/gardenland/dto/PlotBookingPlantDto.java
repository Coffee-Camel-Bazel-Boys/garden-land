package ca.ccbmb.gardenland.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PlotBookingPlantDto {
    private String plotBookingPlantNumber;
    private String plotBookingNumber;
    private String name;
    private String description;
}
