package ca.ccbmb.gardenland.core.user;

import ca.ccbmb.gardenland.assembler.UserAssembler;
import ca.ccbmb.gardenland.dto.UserDto;
import ca.ccbmb.gardenland.exception.UserNotFoundException;
import ca.ccbmb.gardenland.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository repository;
    private final UserAssembler assembler;
    private final UserValidator validator;

    public UserDto create(UserDto dto) {
        validateForSaveAndThrow(dto);
        return assembler.assemble(repository.saveAndFlush(assembler.disassemble(dto)));
    }

    public UserDto update(UserDto dto) {
        validateForSaveAndThrow(dto);

        User entity = getByNumber(dto.getUserNumber());
        return assembler.assemble(repository.saveAndFlush(assembler.disassembleInto(entity, dto)));
    }

    public void delete(String userNumber) {
        repository.delete(getByNumber(userNumber));
    }

    public UserDto get(String userNumber) {
        return assembler.assemble(getByNumber(userNumber));
    }

    private User getByNumber(String number) {
        return repository.findByUserNumber(Integer.valueOf(number))
                .orElseThrow(() -> new UserNotFoundException(number));
    }

    private void validateForSaveAndThrow(UserDto dto) {
        Map<String, String> errors = validator.validateForSave(dto);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
