package ca.ccbmb.gardenland.core.land.availability;

import ca.ccbmb.gardenland.core.land.Land;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "land_availability")
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(of = {"landAvailabilityId"}, callSuper = false)
public class LandAvailability {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "land_availability_id")
    private UUID landAvailabilityId;

    @Column(name = "land_availability_number")
    @Generated(GenerationTime.INSERT)
    private int landAvailabilityNumber;

    @Type(type = "uuid-char")
    @Column(name = "land_id")
    private UUID landId;

    @Column(name = "day_of_week")
    @Setter
    private String dayOfWeek;

    @Column(name = "start_time")
    @Setter
    private LocalTime startTime;

    @Column(name = "end_time")
    @Setter
    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "land_id", insertable = false, updatable = false)
    private Land land;

    public static LandAvailability newInstance(UUID landId) {
        LandAvailability landAvailability = new LandAvailability();
        landAvailability.landAvailabilityId = UUID.randomUUID();
        landAvailability.landId = landId;
        return landAvailability;
    }
}
