package ca.ccbmb.gardenland.core.plot.booking;

import ca.ccbmb.gardenland.core.plot.Plot;
import ca.ccbmb.gardenland.core.plot.booking.plant.BookingPlant;
import ca.ccbmb.gardenland.core.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "plot_booking")
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(of = {"plotBookingId"}, callSuper = false)
public class PlotBooking {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "plot_booking_id")
    private UUID plotBookingId;

    @Column(name = "plot_booking_number")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int plotBookingNumber;

    @Type(type = "uuid-char")
    @Column(name = "plot_id")
    private UUID plotId;

    @Type(type = "uuid-char")
    @Column(name = "usr_id")
    private UUID userId;

    @Column(name = "start_date")
    @Setter
    private LocalDate startDate;

    @Column(name = "end_date")
    @Setter
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plot_id", insertable = false, updatable = false)
    private Plot plot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id", insertable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "plotBooking", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private Set<BookingPlant> plants = new HashSet<>();

    public static PlotBooking newInstance(UUID plotId, UUID userId) {
        PlotBooking plotBooking = new PlotBooking();
        plotBooking.plotBookingId = UUID.randomUUID();
        plotBooking.plotId = plotId;
        plotBooking.userId = userId;

        return plotBooking;
    }

}
