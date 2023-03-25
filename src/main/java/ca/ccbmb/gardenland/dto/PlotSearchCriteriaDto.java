package ca.ccbmb.gardenland.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PlotSearchCriteriaDto {
    private List<String> plotTypeNumbers;
    private BigDecimal minimumPrice;
    private BigDecimal maximumPrice;
    private BigDecimal minimumSize;
    private BigDecimal maximumSize;
}
