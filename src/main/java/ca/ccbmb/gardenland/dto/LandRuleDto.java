package ca.ccbmb.gardenland.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LandRuleDto {
    private String landRuleNumber;
    private String landNumber;
    private String description;
}
