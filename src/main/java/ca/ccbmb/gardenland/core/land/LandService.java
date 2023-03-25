package ca.ccbmb.gardenland.core.land;

import ca.ccbmb.gardenland.assembler.LandAssembler;
import ca.ccbmb.gardenland.dto.LandDto;
import ca.ccbmb.gardenland.dto.LandSearchCriteriaDto;
import ca.ccbmb.gardenland.exception.LandNotFoundException;
import ca.ccbmb.gardenland.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class LandService {
    private final LandAssembler assembler;
    private final LandRepository repository;
    private final LandValidator validator;

    public LandDto get(String landNumber) {
        return assembler.assemble(getByNumber(landNumber));
    }

    public Page<LandDto> find(LandSearchCriteriaDto searchCriteria, Pageable pageable) {
        return repository.findAll(new LandSpecification(searchCriteria), pageable)
                .map(assembler::assemble);
    }

    public LandDto create(LandDto dto) {
        validateForSaveAndThrow(dto);

        return assembler.assemble(repository.saveAndFlush(assembler.disassemble(dto)));
    }

    public LandDto update(LandDto dto) {
        validateForSaveAndThrow(dto);
        Land entity = getByNumber(dto.getLandNumber());

        return assembler.assemble(repository.saveAndFlush(assembler.disassembleInto(entity, dto)));
    }

    public void delete(String landNumber) {
        repository.delete(getByNumber(landNumber));
    }

    private Land getByNumber(String number) {
        return repository.findByLandNumber(Integer.valueOf(number))
                .orElseThrow(() -> new LandNotFoundException(number));
    }

    private void validateForSaveAndThrow(LandDto dto) {
        Map<String, String> errors = validator.validateForSave(dto);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
