package ca.ccbmb.gardenland.controller;

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
    @GetMapping("/{landNumber}")
    public LandDto get(@PathVariable String landNumber) {
        return null;
    }

    @GetMapping("")
    public Page<LandDto> search(LandSearchCriteriaDto searchCriteria, Pageable pageable) {
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LandDto create(@RequestBody LandDto landDto) {
        return null;
    }

    @PutMapping("/{landNumber}")
    public LandDto update(@PathVariable String landNumber, @RequestBody LandDto landDto) {
        landDto.setLandNumber(landNumber);
        return null;
    }

    @DeleteMapping("/{landNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String landNumber) {

    }
}
