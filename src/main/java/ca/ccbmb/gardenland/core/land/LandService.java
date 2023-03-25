package ca.ccbmb.gardenland.core.land;

import ca.ccbmb.gardenland.assembler.LandAssembler;
import ca.ccbmb.gardenland.dto.LandDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LandService {
    private final LandAssembler assembler;
    private final LandRepository repository;
    private final LandValidator validator;

    public Land create(LandDto dto) {
        return null
    }
}
