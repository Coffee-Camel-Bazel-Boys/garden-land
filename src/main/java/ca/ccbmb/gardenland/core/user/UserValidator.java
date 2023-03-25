package ca.ccbmb.gardenland.core.user;

import ca.ccbmb.gardenland.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserValidator {
    private static final String NAME_REQUIRED = "NAME_REQUIRED";

    public Map<String, String> validateForSave(UserDto userDto) {
        Map<String, String> validationErrors = new HashMap<>();

        if (userDto.getName() == null) {
            validationErrors.put("name", NAME_REQUIRED);
        }

        return validationErrors;
    }
}
