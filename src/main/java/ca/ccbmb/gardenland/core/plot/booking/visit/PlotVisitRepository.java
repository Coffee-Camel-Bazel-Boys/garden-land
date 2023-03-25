package ca.ccbmb.gardenland.core.plot.booking.visit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface PlotVisitRepository extends JpaRepository<PlotVisit, UUID>, JpaSpecificationExecutor<PlotVisit> {
    Optional<PlotVisit> findByPlotVisitNumber(int plotVisitNumber);
}