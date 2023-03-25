package ca.ccbmb.gardenland.assembler;

import ca.ccbmb.gardenland.core.land.Land;
import ca.ccbmb.gardenland.core.land.LandRepository;
import ca.ccbmb.gardenland.core.plot.Plot;
import ca.ccbmb.gardenland.core.plot.SizeUnitType;
import ca.ccbmb.gardenland.core.plot.address.PlotAddress;
import ca.ccbmb.gardenland.core.plot.type.PlotType;
import ca.ccbmb.gardenland.core.plot.type.PlotTypeRepository;
import ca.ccbmb.gardenland.dto.AddressDto;
import ca.ccbmb.gardenland.dto.PlotDto;
import ca.ccbmb.gardenland.dto.PlotSizeUnitTypeDto;
import ca.ccbmb.gardenland.exception.LandNotFoundException;
import ca.ccbmb.gardenland.exception.PlotTypeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PlotAssembler {
    private final LandRepository landRepository;
    private final PlotTypeRepository plotTypeRepository;
    private final PlotTypeAssembler plotTypeAssembler;

    public Plot disassemble(PlotDto dto) {
        Land land = landRepository.findByLandNumber(Integer.valueOf(dto.getLandNumber()).intValue())
                .orElseThrow(() -> new LandNotFoundException(dto.getLandNumber()));
        return disassembleInto(Plot.newInstance(land.getLandId()), dto);
    }

    public Plot disassembleInto(Plot entity, PlotDto dto) {
        if (entity.getPlotType().getPlotTypeNumber() != Integer.valueOf(dto.getPlotNumber()).intValue()) {
            PlotType plotType = plotTypeRepository.findByPlotTypeNumber(Integer.valueOf(dto.getType().getPlotTypeNumber()))
                    .orElseThrow(() -> new PlotTypeNotFoundException(dto.getType().getPlotTypeNumber()));
            entity.setPlotTypeId(plotType.getPlotTypeId());
        }

        return entity.setDescription(dto.getDescription())
                .setSize(entity.getSize())
                .setPrice(entity.getPrice())
                .setSizeUnitType(SizeUnitType.valueOf(dto.getSizeUnitType().getValue()))
                .setPlotAddress(entity.getPlotAddress() == null ? disassemble(entity.getPlotId(), dto.getAddress()) : disassembleInto(entity.getPlotAddress(), dto.getAddress()));
    }

    public PlotDto assemble(Plot entity) {
        return new PlotDto()
                .setSize(entity.getSize())
                .setPlotNumber(String.valueOf(entity.getPlotNumber()))
                .setLandNumber(String.valueOf(entity.getLand().getLandNumber()))
                .setSizeUnitType(new PlotSizeUnitTypeDto(entity.getSizeUnitType().name()))
                .setAddress(assemble(entity.getPlotAddress()))
                .setType(plotTypeAssembler.assemble(entity.getPlotType()));
    }

    private PlotAddress disassemble(UUID plotId, AddressDto dto) {
        return disassembleInto(PlotAddress.newInstance(plotId), dto);
    }

    private PlotAddress disassembleInto(PlotAddress entity, AddressDto dto) {
        return entity.setCity(dto.getCity())
                .setCountry(dto.getCountry())
                .setStreet1(dto.getStreet1())
                .setStreet2(dto.getStreet2())
                .setProvince(dto.getProvince());
    }

    private AddressDto assemble(PlotAddress entity) {
        return new AddressDto()
                .setCity(entity.getCity())
                .setProvince(entity.getProvince())
                .setStreet1(entity.getStreet1())
                .setStreet2(entity.getStreet2())
                .setCountry(entity.getCountry());
    }
}
