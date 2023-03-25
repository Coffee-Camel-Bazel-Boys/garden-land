package ca.ccbmb.gardenland.testutil;

import ca.ccbmb.gardenland.dto.UserDto;

public class UserTestDataFactory {
    public static UserDto createValidUserDto(String userNumber) {
        return new UserDto()
                .setUserNumber(userNumber)
                .setName("Frodo Baggins");
    }
}
