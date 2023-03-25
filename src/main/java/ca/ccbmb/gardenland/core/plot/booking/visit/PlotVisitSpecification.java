package ca.ccbmb.gardenland.core.plot.booking.visit;

import ca.ccbmb.gardenland.core.plot.booking.PlotBooking;
import ca.ccbmb.gardenland.core.user.User;
import ca.ccbmb.gardenland.dto.PlotVisitSearchCriteriaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Objects;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class PlotVisitSpecification implements Specification<PlotVisit> {
    private final PlotVisitSearchCriteriaDto searchCriteria;

    @Override
    public Predicate toPredicate(Root<PlotVisit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.and(Stream.of(
                        filterByPlotBookingNumber(root, cb),
                        filterByStatus(root, cb),
                        filterByStartDate(root, cb),
                        filterByEndDate(root, cb),
                        filterByUserNumber(root, cb))
                .filter(Objects::nonNull)
                .toArray(Predicate[]::new));
    }


    private Predicate filterByPlotBookingNumber(Root<PlotVisit> root, CriteriaBuilder cb) {
        if (searchCriteria.getPlotBookingNumber() == null) {
            return null;
        }
        Join<PlotVisit, PlotBooking> plotBookingJoin = root.join("plotBooking");

        return cb.and(cb.equal(plotBookingJoin.get("plotBooking"), searchCriteria.getPlotBookingNumber()));
    }

    private Predicate filterByUserNumber(Root<PlotVisit> root, CriteriaBuilder cb) {
        if (searchCriteria.getUserNumber() == null) {
            return null;
        }
        Join<PlotVisit, User> userJoin = root.join("user");

        return cb.and(cb.equal(userJoin.get("userNumber"), searchCriteria.getUserNumber()));
    }

    private Predicate filterByStatus(Root<PlotVisit> root, CriteriaBuilder cb) {
        if (searchCriteria.getStatus() == null) {
            return null;
        }

        return cb.and(cb.equal(root.get("status"), PlotVisitStatus.valueOf(searchCriteria.getStatus().getValue())));
    }

    private Predicate filterByStartDate(Root<PlotVisit> root, CriteriaBuilder cb) {
        if (searchCriteria.getStartDate() == null) {
            return null;
        }

        return cb.and(cb.greaterThanOrEqualTo(root.get("date"), searchCriteria.getStartDate()));
    }

    private Predicate filterByEndDate(Root<PlotVisit> root, CriteriaBuilder cb) {
        if (searchCriteria.getEndDate() == null) {
            return null;
        }

        return cb.and(cb.lessThanOrEqualTo(root.get("date"), searchCriteria.getEndDate()));
    }
}
