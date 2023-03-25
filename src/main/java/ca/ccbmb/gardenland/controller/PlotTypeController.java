package ca.ccbmb.gardenland.controller;

import ca.ccbmb.gardenland.core.plot.type.PlotTypeService;
import ca.ccbmb.gardenland.dto.PlotTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plot-types")
@RequiredArgsConstructor
public class PlotTypeController {

    private final PlotTypeService service;

    @GetMapping("")
    public List<PlotTypeDto> findAll() {
        return service.findAllPlotTypes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlotTypeDto create(@RequestBody PlotTypeDto plotTypeDto) {
        return service.create(plotTypeDto);
    }

    @DeleteMapping("/{plotTypeNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String plotTypeNumber) {
        service.delete(plotTypeNumber);
    }
}
