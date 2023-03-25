package ca.ccbmb.gardenland.core.land;

import ca.ccbmb.gardenland.dto.LandDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LandValidator {

    public Map<String, String> validateForSave(LandDto landDto) {
        Map<String, String> validationErrors = new HashMap<>();

        return validationErrors;
    }
}
