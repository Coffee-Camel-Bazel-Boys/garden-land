package ca.ccbmb.gardenland.core.plot.booking.plant;

import ca.ccbmb.gardenland.core.plot.booking.PlotBooking;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "plot_booking_plant")
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(of = {"plotBookingPlantId"}, callSuper = false)
public class BookingPlant {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "plot_booking_plant_id")
    private UUID plotBookingPlantId;

    @Column(name = "plot_booking_plant_number")
    @Generated(GenerationTime.INSERT)
    private int plotBookingPlantNumber;

    @Type(type = "uuid-char")
    @Column(name = "plot_booking_id")
    private UUID plotBookingId;

    @Column(name = "name")
    @Setter
    private String name;

    @Column(name = "description")
    @Setter
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plot_booking_id", insertable = false, updatable = false)
    private PlotBooking plotBooking;

    public static BookingPlant newInstance(UUID plotBookingId) {
        BookingPlant bookingPlant = new BookingPlant();
        bookingPlant.plotBookingPlantId = UUID.randomUUID();
        bookingPlant.plotBookingId = plotBookingId;
        return bookingPlant;
    }
}
