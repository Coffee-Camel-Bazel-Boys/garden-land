package ca.ccbmb.gardenland.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {
    private String userNumber;
    private String name;
}
