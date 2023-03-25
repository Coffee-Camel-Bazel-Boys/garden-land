package ca.ccbmb.gardenland.core.plot.booking.visit;

import ca.ccbmb.gardenland.assembler.PlotVisitAssembler;
import ca.ccbmb.gardenland.dto.PlotVisitDto;
import ca.ccbmb.gardenland.dto.PlotVisitSearchCriteriaDto;
import ca.ccbmb.gardenland.exception.PlotVisitNotFoundException;
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
public class PlotVisitService {
    private final PlotVisitAssembler assembler;
    private final PlotVisitRepository repository;
    private final PlotVisitValidator validator;

    public PlotVisitDto get(String plotVisitNumber) {
        return assembler.assemble(getByNumber(plotVisitNumber));
    }

    public Page<PlotVisitDto> find(PlotVisitSearchCriteriaDto searchCriteria, Pageable pageable) {
        return repository.findAll(new PlotVisitSpecification(searchCriteria), pageable)
                .map(assembler::assemble);
    }

    public PlotVisitDto create(PlotVisitDto dto) {
        validateForSaveAndThrow(dto);

        return assembler.assemble(repository.save(assembler.disassemble(dto)));
    }

    public PlotVisitDto update(PlotVisitDto dto) {
        validateForSaveAndThrow(dto);
        PlotVisit entity = getByNumber(dto.getPlotVisitNumber());

        return assembler.assemble(repository.save(assembler.disassembleInto(entity, dto)));
    }

    public void delete(String visitNumber) {
        repository.delete(getByNumber(visitNumber));
    }

    public PlotVisitDto approve(String visitNumber) {
        PlotVisit entity = getByNumber(visitNumber);
        validateForStatusChangeAndThrow(entity);
        entity.approve();
        return assembler.assemble(repository.save(entity));
    }

    public PlotVisitDto cancel(String visitNumber) {
        PlotVisit entity = getByNumber(visitNumber);
        validateForStatusChangeAndThrow(entity);
        entity.cancel();
        return assembler.assemble(repository.save(entity));
    }

    private PlotVisit getByNumber(String number) {
        return repository.findByPlotVisitNumber(Integer.valueOf(number))
                .orElseThrow(() -> new PlotVisitNotFoundException(number));
    }

    private void validateForSaveAndThrow(PlotVisitDto dto) {
        Map<String, String> errors = validator.validateForSave(dto);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    private void validateForStatusChangeAndThrow(PlotVisit entity) {
        Map<String, String> errors = validator.validateForStatusChange(entity);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
