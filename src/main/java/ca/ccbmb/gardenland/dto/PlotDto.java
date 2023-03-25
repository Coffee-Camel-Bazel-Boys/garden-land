package ca.ccbmb.gardenland.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
public class PlotDto {
    private String plotNumber;
    private String landNumber;
    private PlotTypeDto type;
    private BigDecimal price;
    private AddressDto address;
    private BigDecimal size;
    private PlotSizeUnitTypeDto sizeUnitType;
    private String description;
}
