package ca.ccbmb.gardenland.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Accessors(chain = true)
public class LandAvailabilityDto {
    private String landAvailabilityNumber;
    private String landNumber;
    private DayOfWeek day;
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime startTime;
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime endTime;
}
