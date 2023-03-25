package ca.ccbmb.gardenland.core.plot.booking;

import ca.ccbmb.gardenland.core.plot.Plot;
import ca.ccbmb.gardenland.core.user.User;
import ca.ccbmb.gardenland.dto.PlotBookingSearchCriteriaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Objects;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class PlotBookingSpecification implements Specification<PlotBooking> {
    private final PlotBookingSearchCriteriaDto searchCriteria;

    @Override
    public Predicate toPredicate(Root<PlotBooking> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.and(Stream.of(
                        filterByPlotNumber(root, cb),
                        filterByUserNumber(root, cb))
                .filter(Objects::nonNull)
                .toArray(Predicate[]::new));
    }


    private Predicate filterByPlotNumber(Root<PlotBooking> root, CriteriaBuilder cb) {
        if (searchCriteria.getPlotNumber() == null) {
            return null;
        }
        Join<PlotBooking, Plot> plotJoin = root.join("plot");

        return cb.and(cb.equal(plotJoin.get("plotNumber"), searchCriteria.getPlotNumber()));
    }

    private Predicate filterByUserNumber(Root<PlotBooking> root, CriteriaBuilder cb) {
        if (searchCriteria.getUserNumber() == null) {
            return null;
        }
        Join<PlotBooking, User> userJoin = root.join("user");

        return cb.and(cb.equal(userJoin.get("userNumber"), searchCriteria.getUserNumber()));
    }
}
