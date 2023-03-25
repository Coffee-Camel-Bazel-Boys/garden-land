package ca.ccbmb.gardenland.core.plot.booking;

import ca.ccbmb.gardenland.assembler.PlotBookingAssembler;
import ca.ccbmb.gardenland.dto.PlotBookingDto;
import ca.ccbmb.gardenland.dto.PlotBookingSearchCriteriaDto;
import ca.ccbmb.gardenland.exception.PlotBookingNotFoundException;
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
public class PlotBookingService {
    private final PlotBookingRepository repository;
    private final PlotBookingAssembler assembler;
    private final PlotBookingValidator validator;

    public PlotBookingDto get(String plotBookingNumber) {
        return assembler.assemble(getByNumber(plotBookingNumber));
    }

    public Page<PlotBookingDto> find(PlotBookingSearchCriteriaDto searchCriteria, Pageable pageable) {
        return repository.findAll(new PlotBookingSpecification(searchCriteria), pageable)
                .map(assembler::assemble);
    }

    public PlotBookingDto create(PlotBookingDto dto) {
        validateForSaveAndThrow(dto);

        return assembler.assemble(repository.save(assembler.disassemble(dto)));
    }

    public PlotBookingDto update(PlotBookingDto dto) {
        validateForSaveAndThrow(dto);
        PlotBooking entity = getByNumber(dto.getPlotBookingNumber());

        return assembler.assemble(repository.save(assembler.disassembleInto(entity, dto)));
    }

    public void delete(String plotBookingNumber) {
        repository.delete(getByNumber(plotBookingNumber));
    }

    private PlotBooking getByNumber(String number) {
        return repository.findByPlotBookingNumber(Integer.valueOf(number))
                .orElseThrow(() -> new PlotBookingNotFoundException(number));
    }

    private void validateForSaveAndThrow(PlotBookingDto dto) {
        Map<String, String> errors = validator.validateForSave(dto);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
