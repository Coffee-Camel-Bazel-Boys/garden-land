package ca.ccbmb.gardenland.assembler;

import ca.ccbmb.gardenland.core.plot.Plot;
import ca.ccbmb.gardenland.core.plot.PlotRepository;
import ca.ccbmb.gardenland.core.plot.booking.PlotBooking;
import ca.ccbmb.gardenland.core.plot.booking.plant.BookingPlant;
import ca.ccbmb.gardenland.core.user.User;
import ca.ccbmb.gardenland.core.user.UserRepository;
import ca.ccbmb.gardenland.dto.PlotBookingDto;
import ca.ccbmb.gardenland.dto.PlotBookingPlantDto;
import ca.ccbmb.gardenland.exception.PlotNotFoundException;
import ca.ccbmb.gardenland.exception.UserNotFoundException;
import ca.ccbmb.gardenland.util.CollectionComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlotBookingAssembler {
    private final PlotRepository plotRepository;
    private final UserRepository userRepository;

    public PlotBookingDto assemble(PlotBooking entity) {
        return new PlotBookingDto()
                .setPlotBookingNumber(String.valueOf(entity.getPlotBookingNumber()))
                .setPlotNumber(String.valueOf(entity.getPlot().getPlotNumber()))
                .setEndDate(entity.getEndDate())
                .setStartDate(entity.getStartDate())
                .setPlants(assemblePlants(entity.getPlants()));
    }

    public PlotBooking disassemble(PlotBookingDto dto) {
        Plot plot = plotRepository.findByPlotNumber(Integer.valueOf(dto.getPlotNumber()).intValue())
                .orElseThrow(() -> new PlotNotFoundException(dto.getPlotNumber()));
        User user = userRepository.findByUserNumber(Integer.valueOf(dto.getUserNumber()).intValue())
                .orElseThrow(() -> new UserNotFoundException(dto.getUserNumber()));

        return disassembleInto(PlotBooking.newInstance(plot.getPlotId(), user.getUserId()), dto);
    }

    public PlotBooking disassembleInto(PlotBooking entity, PlotBookingDto dto) {
        return entity
                .setStartDate(dto.getStartDate())
                .setEndDate(dto.getEndDate())
                .setPlants(disassemblePlantInto(entity.getPlotBookingId(), entity.getPlants(), dto.getPlants()));
    }

    private List<PlotBookingPlantDto> assemblePlants(Set<BookingPlant> entities) {
        return entities.stream()
                .map(entity -> assemble(entity))
                .collect(Collectors.toList());
    }

    private PlotBookingPlantDto assemble(BookingPlant entity) {
        return new PlotBookingPlantDto()
                .setPlotBookingNumber(String.valueOf(entity.getPlotBooking().getPlotBookingNumber()))
                .setPlotBookingPlantNumber(String.valueOf(entity.getPlotBookingPlantNumber()))
                .setName(entity.getName())
                .setDescription(entity.getDescription());
    }

    private BookingPlant disassemble(UUID plotBookingId, PlotBookingPlantDto dto) {
        return disassembleInto(BookingPlant.newInstance(plotBookingId), dto);
    }

    private BookingPlant disassembleInto(BookingPlant entity, PlotBookingPlantDto dto) {
        return entity
                .setName(dto.getName())
                .setDescription(dto.getDescription());
    }

    private Set<BookingPlant> disassemblePlantInto(UUID plotBookingId, Set<BookingPlant> to, List<PlotBookingPlantDto> from) {
        return CollectionComparator.of(to, from)
                .compareWith((t, f) -> t.getPlotBookingPlantNumber() == Integer.valueOf(f.getPlotBookingPlantNumber()).intValue())
                .collectInto(to, this::disassembleInto, (plantDto) -> disassemble(plotBookingId, plantDto));
    }
}
