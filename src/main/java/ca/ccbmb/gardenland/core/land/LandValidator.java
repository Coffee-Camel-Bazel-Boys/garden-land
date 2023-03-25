package ca.ccbmb.gardenland.core.land;

import ca.ccbmb.gardenland.dto.LandAvailabilityDto;
import ca.ccbmb.gardenland.dto.LandDto;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class LandValidator {
    private static final String DAY_REQUIRED = "DAY_REQUIRED";
    private static final String START_TIME_REQUIRED = "START_TIME_REQUIRED";
    private static final String START_TIME_AFTER_END_TIME = "START_TIME_AFTER_END_TIME";
    private static final String END_TIME_REQUIRED = "END_TIME_REQUIRED";

    public Map<String, String> validateForSave(LandDto landDto) {
        Map<String, String> validationErrors = new HashMap<>();

        for (int i = 0; i < landDto.getAvailabilities().size(); i++) {
            validationErrors.putAll(validateAvailabilityForSave(i, landDto.getAvailabilities().get(i)));
        }

        return validationErrors;
    }

    private Map<String, String> validateAvailabilityForSave(int index, LandAvailabilityDto availabilityDto) {
        Map<String, String> validationErrors = new HashMap<>();
        if (availabilityDto.getDay() == null) {
            validationErrors.put(String.format("availability.%s.day", index), DAY_REQUIRED);
        }

        if (availabilityDto.getStartTime() == null) {
            validationErrors.put(String.format("availability.%s.startTime", index), START_TIME_REQUIRED);
        }

        if (availabilityDto.getEndTime() == null) {
            validationErrors.put(String.format("availability.%s.endTime", index), END_TIME_REQUIRED);
        }

        if (!isStartTimeBeforeEndTime(availabilityDto.getStartTime(), availabilityDto.getEndTime())) {
            validationErrors.put(String.format("availability.%s.startTime", index), START_TIME_AFTER_END_TIME);
        }

        return validationErrors;
    }

    private boolean isStartTimeBeforeEndTime(LocalTime startTime, LocalTime endTime) {
        if (startTime == null || endTime == null) {
            return true;
        }

        return startTime.isBefore(endTime);
    }
}
