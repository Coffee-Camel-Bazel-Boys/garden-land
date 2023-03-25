package ca.ccbmb.gardenland.controller;

import ca.ccbmb.gardenland.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plots")
@RequiredArgsConstructor
public class PlotController {
    @GetMapping("/{plotNumber}")
    public PlotDto get(@PathVariable String plotNumber) {
        return null;
    }

    @GetMapping("")
    public Page<PlotDto> search(PlotSearchCriteriaDto searchCriteria, Pageable pageable) {
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlotDto create(@RequestBody PlotDto plotDto) {
        return null;
    }

    @PutMapping("/{plotNumber}")
    public PlotDto update(@PathVariable String plotNumber, @RequestBody PlotDto plotDto) {
        plotDto.setPlotNumber(plotNumber);
        return null;
    }

    @DeleteMapping("/{plotNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String plotNumber) {

    }

    @GetMapping("/types")
    public List<PlotTypeDto> findAll() {
        return null;
    }

}
