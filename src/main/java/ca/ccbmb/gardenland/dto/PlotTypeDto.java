package ca.ccbmb.gardenland.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PlotTypeDto {
    private String plotTypeNumber;
    private String name;
}
