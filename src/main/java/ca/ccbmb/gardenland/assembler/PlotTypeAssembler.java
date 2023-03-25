package ca.ccbmb.gardenland.assembler;

import ca.ccbmb.gardenland.core.plot.type.PlotType;
import ca.ccbmb.gardenland.dto.PlotTypeDto;
import org.springframework.stereotype.Component;

@Component
public class PlotTypeAssembler {

    public PlotTypeDto assemble(PlotType entity) {
        return new PlotTypeDto()
                .setName(entity.getName())
                .setPlotTypeNumber(String.valueOf(entity.getPlotTypeNumber()));
    }

    public PlotType disassemble(PlotTypeDto dto) {
        return PlotType.newInstance()
                .setName(dto.getName());
    }
}
