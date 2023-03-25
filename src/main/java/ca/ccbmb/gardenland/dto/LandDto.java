package ca.ccbmb.gardenland.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class LandDto {
    private String landNumber;
    private String userNumber;
    private String description;
    private List<LandRuleDto> rules;
    private List<LandAvailabilityDto> availabilities;

}
