package ca.ccbmb.gardenland.core.plot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlotRepository extends JpaRepository<Plot, UUID>, JpaSpecificationExecutor<Plot> {
    Optional<Plot> findByPlotNumber(int plotNumber);
}
