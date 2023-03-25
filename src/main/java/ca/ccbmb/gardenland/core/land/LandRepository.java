package ca.ccbmb.gardenland.core.land;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LandRepository extends JpaRepository<Land, UUID> {
}
