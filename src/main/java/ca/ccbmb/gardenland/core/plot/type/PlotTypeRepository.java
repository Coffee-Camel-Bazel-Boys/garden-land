package ca.ccbmb.gardenland.core.plot.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlotTypeRepository extends JpaRepository<PlotType, UUID> {
    Optional<PlotType> findByPlotTypeNumber(int plotTypeNumber);
}
