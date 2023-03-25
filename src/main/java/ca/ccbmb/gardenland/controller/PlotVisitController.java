package ca.ccbmb.gardenland.controller;

import ca.ccbmb.gardenland.dto.PlotVisitDto;
import ca.ccbmb.gardenland.dto.PlotVisitSearchCriteriaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/plot-visits")
@RequiredArgsConstructor
public class PlotVisitController {
    @GetMapping("/{plotVisitNumber}")
    public PlotVisitDto get(@PathVariable String plotVisitNumber) {
        return null;
    }

    @GetMapping("")
    public Page<PlotVisitDto> search(PlotVisitSearchCriteriaDto searchCriteria, Pageable pageable) {
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlotVisitDto create(@RequestBody PlotVisitDto plotVisitDto) {
        return null;
    }

    @PutMapping("/{plotVisitNumber}")
    public PlotVisitDto update(@PathVariable String plotVisitNumber, @RequestBody PlotVisitDto plotVisitDto) {
        plotVisitDto.setPlotVisitNumber(plotVisitNumber);
        return null;
    }

    @PutMapping("/{plotVisitNumber}/approve")
    public PlotVisitDto approve(@PathVariable String plotVisitNumber) {
        return null;
    }

    @PutMapping("/{plotVisitNumber}/cancel")
    public PlotVisitDto cancel(@PathVariable String plotVisitNumber) {
        return null;
    }
}
