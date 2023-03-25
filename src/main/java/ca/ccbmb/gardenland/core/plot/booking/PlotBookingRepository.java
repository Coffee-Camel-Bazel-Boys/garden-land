package ca.ccbmb.gardenland.core.plot.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlotBookingRepository extends JpaRepository<PlotBooking, UUID>, JpaSpecificationExecutor<PlotBooking> {
    Optional<PlotBooking> findByPlotBookingNumber(int plotBookingNumber);
}
