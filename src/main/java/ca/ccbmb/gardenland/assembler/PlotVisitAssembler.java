package ca.ccbmb.gardenland.assembler;

import ca.ccbmb.gardenland.core.plot.booking.PlotBooking;
import ca.ccbmb.gardenland.core.plot.booking.PlotBookingRepository;
import ca.ccbmb.gardenland.core.plot.booking.visit.PlotVisit;
import ca.ccbmb.gardenland.dto.PlotVisitDto;
import ca.ccbmb.gardenland.exception.PlotBookingNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlotVisitAssembler {
    private final PlotBookingRepository plotBookingRepository;

    public PlotVisit disassemble(PlotVisitDto dto) {
        PlotBooking plotBooking = plotBookingRepository.findByPlotBookingNumber(Integer.valueOf(dto.getPlotBookingNumber()).intValue())
                .orElseThrow(() -> new PlotBookingNotFoundException(dto.getPlotBookingNumber()));
        return disassembleInto(PlotVisit.newInstance(plotBooking.getPlotBookingId()).setPlotBooking(plotBooking), dto);
    }

    public PlotVisit disassembleInto(PlotVisit entity, PlotVisitDto dto) {
        return entity
                .setDate(dto.getDate())
                .setStartTime(dto.getStartTime())
                .setEndTime(dto.getEndTime());
    }

    public PlotVisitDto assemble(PlotVisit entity) {
        return new PlotVisitDto()
                .setPlotVisitNumber(String.valueOf(entity.getPlotVisitNumber()))
                .setDate(entity.getDate())
                .setStartTime(entity.getStartTime())
                .setEndTime(entity.getEndTime())
                .setPlotBookingNumber(String.valueOf(entity.getPlotBooking().getPlotBookingNumber()));
    }
}
