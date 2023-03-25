package ca.ccbmb.gardenland.controller;

import ca.ccbmb.gardenland.dto.PlotBookingDto;
import ca.ccbmb.gardenland.dto.PlotBookingSearchCriteriaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/plot-bookings")
@RequiredArgsConstructor
public class PlotBookingController {
    @GetMapping("/{plotBookingNumber}")
    public PlotBookingDto get(@PathVariable String plotBookingNumber) {
        return null;
    }

    @GetMapping("")
    public Page<PlotBookingDto> search(PlotBookingSearchCriteriaDto searchCriteria, Pageable pageable) {
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlotBookingDto create(@RequestBody PlotBookingDto plotBookingDto) {
        return null;
    }

    @PutMapping("/{plotBookingNumber}")
    public PlotBookingDto update(@PathVariable String plotBookingNumber, @RequestBody PlotBookingDto plotBookingDto) {
        plotBookingDto.setPlotBookingNumber(plotBookingNumber);
        return null;
    }

    @DeleteMapping("/{plotBookingNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String plotBookingNumber) {

    }
}
