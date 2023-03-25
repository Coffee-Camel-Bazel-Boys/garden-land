package ca.ccbmb.gardenland.core.plot;

import ca.ccbmb.gardenland.assembler.PlotAssembler;
import ca.ccbmb.gardenland.dto.PlotDto;
import ca.ccbmb.gardenland.dto.PlotSearchCriteriaDto;
import ca.ccbmb.gardenland.exception.PlotNotFoundException;
import ca.ccbmb.gardenland.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class PlotService {
    private final PlotAssembler assembler;
    private final PlotRepository repository;
    private final PlotValidator validator;

    public PlotDto get(String plotNumber) {
        return assembler.assemble(getByNumber(plotNumber));
    }

    public Page<PlotDto> find(PlotSearchCriteriaDto searchCriteria, Pageable pageable) {
        return repository.findAll(new PlotSpecification(searchCriteria), pageable)
                .map(assembler::assemble);
    }

    public PlotDto create(PlotDto dto) {
        validateForSaveAndThrow(dto);

        return assembler.assemble(repository.saveAndFlush(assembler.disassemble(dto)));
    }

    public PlotDto update(PlotDto dto) {
        validateForSaveAndThrow(dto);
        Plot entity = getByNumber(dto.getPlotNumber());

        return assembler.assemble(repository.saveAndFlush(assembler.disassembleInto(entity, dto)));
    }

    public void delete(String plotNumber) {
        repository.delete(getByNumber(plotNumber));
    }

    private Plot getByNumber(String number) {
        return repository.findByPlotNumber(Integer.valueOf(number))
                .orElseThrow(() -> new PlotNotFoundException(number));
    }

    private void validateForSaveAndThrow(PlotDto dto) {
        Map<String, String> errors = validator.validateForSave(dto);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
