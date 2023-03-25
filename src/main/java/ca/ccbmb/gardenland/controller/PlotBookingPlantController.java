package ca.ccbmb.gardenland.controller;

import ca.ccbmb.gardenland.dto.LandDto;
import ca.ccbmb.gardenland.dto.PlotBookingDto;
import ca.ccbmb.gardenland.dto.PlotBookingPlantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/plot-bookings/{plotBookingNumber}")
@RequiredArgsConstructor
public class PlotBookingPlantController {
    @GetMapping("/plants")
    public Page<PlotBookingPlantDto> findByPlotBookingNumber(@PathVariable String plotBookingNumber, Pageable pageable) {
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlotBookingDto create(@PathVariable String plotBookingNumber, @RequestBody PlotBookingPlantDto plotBookingPlantDto) {
        plotBookingPlantDto.setPlotBookingNumber(plotBookingNumber);
        return null;
    }

    @PutMapping("/{plotBookingPlantNumber}")
    public LandDto update(@PathVariable String plotBookingNumber, @PathVariable String plotBookingPlantNumber, @RequestBody PlotBookingPlantDto plotBookingPlantDto) {
        plotBookingPlantDto.setPlotBookingPlantNumber(plotBookingPlantNumber);
        plotBookingPlantDto.setPlotBookingNumber(plotBookingNumber);
        return null;
    }

    @DeleteMapping("/{plotBookingPlantNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String plotBookingNumber, @PathVariable String plotBookingPlantNumber) {

    }
}
