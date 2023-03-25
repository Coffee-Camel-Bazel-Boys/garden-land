package ca.ccbmb.gardenland.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class PointDto {
    BigDecimal x;
    BigDecimal y;
}
