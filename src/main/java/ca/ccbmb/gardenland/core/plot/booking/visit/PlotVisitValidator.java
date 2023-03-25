package ca.ccbmb.gardenland.core.plot.booking.visit;

import ca.ccbmb.gardenland.dto.PlotVisitDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class PlotVisitValidator {
    private static final String START_TIME_REQUIRED = "START_TIME_REQUIRED";
    private static final String END_TIME_REQUIRED = "END_TIME_REQUIRED";
    private static final String START_TIME_AFTER_END_TIME = "START_TIME_AFTER_END_TIME";
    private static final String DATE_REQUIRED = "DATE_REQUIRED";
    private static final String VISIT_CANCELLED = "VISIT_CANCELLED";
    private static final String DATE_ALREADY_REACHED = "DATE_ALREADY_REACHED";

    public Map<String, String> validateForSave(PlotVisitDto plotVisitDto) {
        Map<String, String> validationErrors = new HashMap<>();

        if (plotVisitDto.getDate() == null) {
            validationErrors.put("date", DATE_REQUIRED);
        }

        if (plotVisitDto.getStartTime() == null) {
            validationErrors.put("startTime", START_TIME_REQUIRED);
        }

        if (plotVisitDto.getEndTime() == null) {
            validationErrors.put("endTime", END_TIME_REQUIRED);
        }

        if (!isStartTimeBeforeEndTime(plotVisitDto.getStartTime(), plotVisitDto.getEndTime())) {
            validationErrors.put("startTime", START_TIME_AFTER_END_TIME);
        }

        return validationErrors;
    }

    public Map<String, String> validateForStatusChange(PlotVisit plotVisit) {
        Map<String, String> validationErrors = new HashMap<>();

        if (plotVisit.getPlotVisitStatus() == PlotVisitStatus.CANCELLED) {
            validationErrors.put("status", VISIT_CANCELLED);
        }

        if (plotVisit.getDate().isBefore(LocalDate.now())) {
            validationErrors.put("status", DATE_ALREADY_REACHED);
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
