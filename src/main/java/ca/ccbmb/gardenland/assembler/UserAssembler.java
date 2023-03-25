package ca.ccbmb.gardenland.assembler;

import ca.ccbmb.gardenland.core.user.User;
import ca.ccbmb.gardenland.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {
    public User disassemble(UserDto dto) {
        return disassembleInto(User.newInstance(), dto);
    }

    public User disassembleInto(User entity, UserDto dto) {
        return entity.setName(dto.getName());
    }

    public UserDto assemble(User entity) {
        return new UserDto()
                .setUserNumber(String.valueOf(entity.getUserNumber()))
                .setName(entity.getName());
    }
}
