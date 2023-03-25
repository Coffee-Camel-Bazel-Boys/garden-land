package ca.ccbmb.gardenland.core.plot.booking.visit;

import ca.ccbmb.gardenland.core.plot.booking.PlotBooking;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "plot_visit")
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(of = {"plotVisitId"}, callSuper = false)
public class PlotVisit {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "plot_visit_id")
    private UUID plotVisitId;

    @Column(name = "plot_visit_number")
    @Generated(GenerationTime.INSERT)
    private int plotVisitNumber;

    @Type(type = "uuid-char")
    @Column(name = "plot_booking_id")
    private UUID plotBookingId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PlotVisitStatus plotVisitStatus;

    @Column(name = "date")
    @Setter
    private LocalDate date;

    @Column(name = "start_time")
    @Setter
    private LocalTime startTime;

    @Column(name = "end_time")
    @Setter
    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plot_booking_id", insertable = false, updatable = false)
    @Setter
    private PlotBooking plotBooking;

    public static PlotVisit newInstance(UUID plotBookingId) {
        PlotVisit plotVisit = new PlotVisit();
        plotVisit.plotVisitId = UUID.randomUUID();
        plotVisit.plotVisitStatus = PlotVisitStatus.PENDING;
        return plotVisit;
    }

    public void approve() {
        this.plotVisitStatus = PlotVisitStatus.APPROVED;
    }

    public void cancel() {
        this.plotVisitStatus = PlotVisitStatus.CANCELLED;
    }
}
