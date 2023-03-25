package ca.ccbmb.gardenland.core.land;

import ca.ccbmb.gardenland.core.user.User;
import ca.ccbmb.gardenland.dto.LandSearchCriteriaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Objects;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class LandSpecification implements Specification<Land> {
    private final LandSearchCriteriaDto searchCriteria;

    @Override
    public Predicate toPredicate(Root<Land> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.and(Stream.of(
                        filterByUserNumber(root, cb))
                .filter(Objects::nonNull)
                .toArray(Predicate[]::new));
    }

    private Predicate filterByUserNumber(Root<Land> root, CriteriaBuilder cb) {
        if (searchCriteria.getUserNumber() == null) {
            return null;
        }
        Join<Land, User> userJoin = root.join("user");

        return cb.and(cb.equal(userJoin.get("userNumber"), searchCriteria.getUserNumber()));
    }
}
