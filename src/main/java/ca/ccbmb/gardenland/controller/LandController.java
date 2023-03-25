package ca.ccbmb.gardenland.controller;

import ca.ccbmb.gardenland.core.land.LandService;
import ca.ccbmb.gardenland.dto.LandDto;
import ca.ccbmb.gardenland.dto.LandSearchCriteriaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lands")
@RequiredArgsConstructor
public class LandController {
    private final LandService service;

    @GetMapping("/{landNumber}")
    public LandDto get(@PathVariable String landNumber) {
        return service.get(landNumber q );
    }

    @GetMapping("")
    public Page<LandDto> search(LandSearchCriteriaDto searchCriteria, Pageable pageable) {
        return service.find(searchCriteria, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LandDto create(@RequestBody LandDto landDto) {
        return service.create(landDto);
    }

    @PutMapping("/{landNumber}")
    public LandDto update(@PathVariable String landNumber, @RequestBody LandDto landDto) {
        landDto.setLandNumber(landNumber);
        return service.update(landDto);
    }

    @DeleteMapping("/{landNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String landNumber) {
        service.delete(landNumber);
    }
}
