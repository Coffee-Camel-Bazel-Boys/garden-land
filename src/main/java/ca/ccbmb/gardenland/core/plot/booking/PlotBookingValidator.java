package ca.ccbmb.gardenland.core.plot.booking;

import ca.ccbmb.gardenland.dto.PlotBookingDto;
import ca.ccbmb.gardenland.dto.PlotBookingPlantDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class PlotBookingValidator {
    private static final String START_DATE_REQUIRED = "START_DATE_REQUIRED";
    private static final String END_DATE_REQUIRED = "END_DATE_REQUIRED";
    private static final String START_DATE_AFTER_END_DATE = "START_DATE_AFTER_END_DATE";
    private static final String NAME_REQUIRED = "NAME_REQUIRED";


    public Map<String, String> validateForSave(PlotBookingDto plotBookingDto) {
        Map<String, String> validationErrors = new HashMap<>();

        if (plotBookingDto.getStartDate() == null) {
            validationErrors.put("startDate", START_DATE_REQUIRED);
        }

        if (plotBookingDto.getEndDate() == null) {
            validationErrors.put("endDate", END_DATE_REQUIRED);
        }

        if (!isStartDateBeforeEndDate(plotBookingDto.getStartDate(), plotBookingDto.getEndDate())) {
            validationErrors.put("startDate", START_DATE_AFTER_END_DATE);
        }

        for (int i = 0; i < plotBookingDto.getPlants().size(); i++) {
            validationErrors.putAll(validatePlantForSave(i, plotBookingDto.getPlants().get(i)));
        }

        return validationErrors;
    }

    private Map<String, String> validatePlantForSave(int index, PlotBookingPlantDto dto) {
        Map<String, String> validationErrors = new HashMap<>();
        if (dto.getName() == null) {
            validationErrors.put(String.format("plants.%s.name", index), NAME_REQUIRED);
        }

        return validationErrors;
    }

    private boolean isStartDateBeforeEndDate(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return true;
        }

        return startDate.isBefore(endDate);
    }
}
