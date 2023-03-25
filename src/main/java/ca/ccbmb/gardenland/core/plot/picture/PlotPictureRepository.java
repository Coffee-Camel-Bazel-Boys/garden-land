package ca.ccbmb.gardenland.core.plot.picture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlotPictureRepository extends JpaRepository<PlotPicture, UUID> {
    List<PlotPicture> findAllByPlot_PlotNumber(int plotNumber);

    Optional<PlotPicture> findByPlotPictureNumber(int plotPictureNumber);
}
