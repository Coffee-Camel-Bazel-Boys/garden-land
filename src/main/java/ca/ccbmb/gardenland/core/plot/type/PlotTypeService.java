package ca.ccbmb.gardenland.core.plot.type;

import ca.ccbmb.gardenland.assembler.PlotTypeAssembler;
import ca.ccbmb.gardenland.dto.PlotTypeDto;
import ca.ccbmb.gardenland.exception.PlotTypeNotFoundException;
import ca.ccbmb.gardenland.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PlotTypeService {
    private final PlotTypeRepository repository;
    private final PlotTypeAssembler assembler;
    private final PlotTypeValidator validator;

    public List<PlotTypeDto> findAllPlotTypes() {
        return repository.findAll().stream()
                .map(assembler::assemble)
                .collect(Collectors.toList());
    }

    public PlotTypeDto create(PlotTypeDto dto) {
        validateForSaveAndThrow(dto);
        return assembler.assemble(repository.saveAndFlush(assembler.disassemble(dto)));
    }

    public void delete(String plotTypeNumber) {
        PlotType entity = getByNumber(plotTypeNumber);
        repository.delete(entity);
    }

    private PlotType getByNumber(String number) {
        return repository.findByPlotTypeNumber(Integer.valueOf(number).intValue())
                .orElseThrow(() -> new PlotTypeNotFoundException(number));
    }

    private void validateForSaveAndThrow(PlotTypeDto dto) {
        Map<String, String> errors = validator.validateForSave(dto);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
