package ca.ccbmb.gardenland.core.plot;

import ca.ccbmb.gardenland.core.plot.type.PlotType;
import ca.ccbmb.gardenland.dto.PlotSearchCriteriaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.util.Objects;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class PlotSpecification implements Specification<Plot> {
    private final PlotSearchCriteriaDto searchCriteria;

    @Override
    public Predicate toPredicate(Root<Plot> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.and(Stream.of(
                        filterByPlotNumbers(root, cb),
                        filterByMaximumPrice(root, cb),
                        filterByMinimumPrice(root, cb),
                        filterByMaximumSize(root, cb),
                        filterByMinimumSize(root, cb))
                .filter(Objects::nonNull)
                .toArray(Predicate[]::new));
    }

    private Predicate filterByPlotNumbers(Root<Plot> root, CriteriaBuilder cb) {
        if (CollectionUtils.isEmpty(searchCriteria.getPlotTypeNumbers())) {
            return null;
        }
        Join<Plot, PlotType> plotTypeJoin = root.join("plotType");

        return cb.and(cb.in(plotTypeJoin.get("plotTypeNumber")).value(searchCriteria.getPlotTypeNumbers()));
    }

    private Predicate filterByMaximumPrice(Root<Plot> root, CriteriaBuilder cb) {
        if (searchCriteria.getMaximumPrice() == null) {
            return null;
        }

        return cb.and(cb.lessThanOrEqualTo(root.get("price"), searchCriteria.getMaximumPrice()));
    }

    private Predicate filterByMinimumPrice(Root<Plot> root, CriteriaBuilder cb) {
        if (searchCriteria.getMinimumPrice() == null) {
            return null;
        }

        return cb.and(cb.lessThanOrEqualTo(root.get("price"), searchCriteria.getMinimumPrice()));
    }

    private Predicate filterByMinimumSize(Root<Plot> root, CriteriaBuilder cb) {
        if (searchCriteria.getMinimumSize() == null) {
            return null;
        }

        return cb.and(cb.lessThanOrEqualTo(root.get("size"), searchCriteria.getMinimumSize()));
    }

    private Predicate filterByMaximumSize(Root<Plot> root, CriteriaBuilder cb) {
        if (searchCriteria.getMaximumSize() == null) {
            return null;
        }

        return cb.and(cb.lessThanOrEqualTo(root.get("size"), searchCriteria.getMaximumSize()));
    }
}
