package ca.ccbmb.gardenland.assembler;

import ca.ccbmb.gardenland.core.land.Land;
import ca.ccbmb.gardenland.core.land.availability.LandAvailability;
import ca.ccbmb.gardenland.core.land.rule.LandRule;
import ca.ccbmb.gardenland.core.user.User;
import ca.ccbmb.gardenland.core.user.UserRepository;
import ca.ccbmb.gardenland.dto.LandAvailabilityDto;
import ca.ccbmb.gardenland.dto.LandDto;
import ca.ccbmb.gardenland.dto.LandRuleDto;
import ca.ccbmb.gardenland.exception.UserNotFoundException;
import ca.ccbmb.gardenland.util.CollectionComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LandAssembler {
    private final UserRepository userRepository;

    public Land disassemble(LandDto dto) {
        User user = userRepository.findByUserNumber(Integer.valueOf(dto.getUserNumber()))
                .orElseThrow(() -> new UserNotFoundException(dto.getUserNumber()));

        return disassembleInto(Land.newInstance(user.getUserId()).setUser(user), dto);
    }

    public Land disassembleInto(Land entity, LandDto dto) {
        return entity.setDescription(dto.getDescription())
                .setRules(disassembleRuleInto(entity.getLandId(), entity.getRules(), dto.getRules()))
                .setAvailabilities(disassembleAvailabilityInto(entity.getLandId(), entity.getAvailabilities(), dto.getAvailabilities()));
    }

    public LandDto assemble(Land entity) {
        return new LandDto()
                .setUserNumber(String.valueOf(entity.getUser().getUserNumber()))
                .setLandNumber(String.valueOf(entity.getLandNumber()))
                .setDescription(entity.getDescription())
                .setRules(assembleRules(String.valueOf(entity.getLandNumber()), entity.getRules()))
                .setAvailabilities(assembleAvailabilities(String.valueOf(entity.getLandNumber()), entity.getAvailabilities()));
    }

    private LandRule disassemble(UUID landId, LandRuleDto dto) {
        return disassembleInto(LandRule.newInstance(landId), dto);
    }

    private LandRule disassembleInto(LandRule entity, LandRuleDto dto) {
        return entity.setDescription(dto.getDescription());
    }

    private List<LandRuleDto> assembleRules(String landNumber, Set<LandRule> entities) {
        return entities.stream()
                .map(entity -> assemble(landNumber, entity))
                .collect(Collectors.toList());
    }

    private LandRuleDto assemble(String landNumber, LandRule entity) {
        return new LandRuleDto()
                .setDescription(entity.getDescription())
                .setLandRuleNumber(String.valueOf(entity.getLandRuleNumber()))
                .setLandNumber(landNumber);
    }

    private Set<LandRule> disassembleRuleInto(UUID landId, Set<LandRule> to, List<LandRuleDto> from) {
        return CollectionComparator.of(to, from)
                .compareWith((t, f) -> t.getLandRuleNumber() == Integer.valueOf(f.getLandRuleNumber()).intValue())
                .collectInto(to, this::disassembleInto, (landRuleDto) -> disassemble(landId, landRuleDto));
    }

    private LandAvailability disassemble(UUID landId, LandAvailabilityDto dto) {
        return disassembleInto(LandAvailability.newInstance(landId), dto);
    }

    private LandAvailability disassembleInto(LandAvailability entity, LandAvailabilityDto dto) {
        return entity.setDayOfWeek(dto.getDay().name())
                .setStartTime(dto.getStartTime())
                .setEndTime(dto.getEndTime());
    }

    private List<LandAvailabilityDto> assembleAvailabilities(String landNumber, Set<LandAvailability> entities) {
        return entities.stream()
                .map(entity -> assemble(landNumber, entity))
                .collect(Collectors.toList());
    }

    private LandAvailabilityDto assemble(String landNumber, LandAvailability entity) {
        return new LandAvailabilityDto()
                .setDay(DayOfWeek.valueOf(entity.getDayOfWeek()))
                .setStartTime(entity.getStartTime())
                .setEndTime(entity.getEndTime());
    }

    private Set<LandAvailability> disassembleAvailabilityInto(UUID landId, Set<LandAvailability> to, List<LandAvailabilityDto> from) {
        return CollectionComparator.of(to, from)
                .compareWith((t, f) -> t.getLandAvailabilityNumber() == Integer.valueOf(f.getLandAvailabilityNumber()).intValue())
                .collectInto(to, this::disassembleInto, (landAvailabilityDto) -> disassemble(landId, landAvailabilityDto));
    }
}
