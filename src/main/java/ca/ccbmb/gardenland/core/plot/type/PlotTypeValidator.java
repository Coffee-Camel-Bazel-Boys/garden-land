package ca.ccbmb.gardenland.core.plot.type;

import ca.ccbmb.gardenland.dto.PlotTypeDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PlotTypeValidator {
    private static final String NAME_REQUIRED = "NAME_REQUIRED";

    public Map<String, String> validateForSave(PlotTypeDto plotTypeDto) {
        Map<String, String> validationErrors = new HashMap<>();

        if (plotTypeDto.getName() == null) {
            validationErrors.put("name", NAME_REQUIRED);
        }

        return validationErrors;
    }
}
