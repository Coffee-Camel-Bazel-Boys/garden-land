package ca.ccbmb.gardenland.controller;

import ca.ccbmb.gardenland.core.plot.PlotService;
import ca.ccbmb.gardenland.dto.PlotDto;
import ca.ccbmb.gardenland.dto.PlotSearchCriteriaDto;
import ca.ccbmb.gardenland.dto.PlotTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plots")
@RequiredArgsConstructor
public class PlotController {
    private final PlotService plotService;

    @GetMapping("/{plotNumber}")
    public PlotDto get(@PathVariable String plotNumber) {
        return plotService.get(plotNumber);
    }

    @GetMapping("")
    public Page<PlotDto> search(PlotSearchCriteriaDto searchCriteria, Pageable pageable) {
        return plotService.find(searchCriteria, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlotDto create(@RequestBody PlotDto plotDto) {
        return plotService.create(plotDto);
    }

    @PutMapping("/{plotNumber}")
    public PlotDto update(@PathVariable String plotNumber, @RequestBody PlotDto plotDto) {
        plotDto.setPlotNumber(plotNumber);
        return plotService.update(plotDto);
    }

    @DeleteMapping("/{plotNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String plotNumber) {
        plotService.delete(plotNumber);
    }

    @GetMapping("/types")
    public List<PlotTypeDto> findAll() {
        return plotService.findAllPlotTypes();
    }

}
