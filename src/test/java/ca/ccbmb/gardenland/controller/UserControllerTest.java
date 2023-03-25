package ca.ccbmb.gardenland.controller;

import ca.ccbmb.gardenland.dto.UserDto;
import ca.ccbmb.gardenland.exception.UserNotFoundException;
import ca.ccbmb.gardenland.exception.ValidationException;
import ca.ccbmb.gardenland.testutil.UserTestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlGroup({
        @Sql(scripts = "classpath:teardown.sql")
})

public class UserControllerTest {
    @Autowired
    private UserController userController;

    private static final String INVALID_USER_NUMBER = "999999";

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testCreateValidUser_shouldCreateUser() {
        UserDto input = UserTestDataFactory.createValidUserDto(null);

        UserDto result = userController.create(input);
        assertAll(
                () -> assertEquals(input.getName(), result.getName(), "name"),
                () -> assertNotNull(result.getUserNumber(), "number")
        );
    }

    @Test
    public void testCreateInvalidUser_shouldThrowValidationException() {
        UserDto input = UserTestDataFactory.createValidUserDto(null).setName(null);

        assertThrows(ValidationException.class, () -> userController.create(input));
    }

    @Test
    public void testUpdateValidUser_shouldUpdateExistingUser() {
        UserDto existingUser = userController.create(UserTestDataFactory.createValidUserDto(null));
        UserDto input = UserTestDataFactory.createValidUserDto(existingUser.getUserNumber()).setName("Samwise Gamgee");

        UserDto result = userController.update(existingUser.getUserNumber(), input);
        assertAll(
                () -> assertEquals(input.getName(), result.getName(), "name"),
                () -> assertEquals(existingUser.getUserNumber(), result.getUserNumber(), "number")
        );
    }

    @Test
    public void testUpdateInvalidUser_shouldThrowValidationException() {
        UserDto existingUser = userController.create(UserTestDataFactory.createValidUserDto(null));
        UserDto input = UserTestDataFactory.createValidUserDto(existingUser.getUserNumber()).setName(null);

        assertThrows(ValidationException.class, () -> userController.update(existingUser.getUserNumber(), input));
    }

    @Test
    public void testUpdateInvalidUserNumber_shouldThrowUserNotFoundException() {
        UserDto existingUser = userController.create(UserTestDataFactory.createValidUserDto(null));
        UserDto input = UserTestDataFactory.createValidUserDto(INVALID_USER_NUMBER);

        assertThrows(UserNotFoundException.class, () -> userController.update(INVALID_USER_NUMBER, input));
    }

    @Test
    public void testGetValidUser_shouldReturnCorrespondingUser() {
        UserDto existingUser = userController.create(UserTestDataFactory.createValidUserDto(null));

        UserDto result = userController.get(existingUser.getUserNumber());

        assertAll(
                () -> assertEquals(existingUser.getName(), result.getName(), "name"),
                () -> assertEquals(existingUser.getUserNumber(), result.getUserNumber(), "number")
        );
    }

    @Test
    public void testGetValidUserWithInvalidUserNumber_shouldThrowNotFoundException() {
        UserDto existingUser = userController.create(UserTestDataFactory.createValidUserDto(null));

        assertThrows(UserNotFoundException.class, () -> userController.get(INVALID_USER_NUMBER));
    }

    @Test
    public void testGetValidUserWithInvalidUserNumber_shouldDeleteUser() {
        UserDto existingUser = userController.create(UserTestDataFactory.createValidUserDto(null));
        String input = existingUser.getUserNumber();

        assertNotNull(userController.get(input));
        userController.delete(input);
        assertThrows(UserNotFoundException.class, () -> userController.get(input));
    }
}
