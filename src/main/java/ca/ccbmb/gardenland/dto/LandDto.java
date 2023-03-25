package ca.ccbmb.gardenland.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class LandDto {
    private String landNumber;
    private String userNumber;
    private String description;
    private List<LandRuleDto> rules = new ArrayList<>();
    private List<LandAvailabilityDto> availabilities = new ArrayList<>();

}
