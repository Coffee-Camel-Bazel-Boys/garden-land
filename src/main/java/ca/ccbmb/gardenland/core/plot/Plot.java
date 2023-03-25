package ca.ccbmb.gardenland.core.plot;

import ca.ccbmb.gardenland.core.land.Land;
import ca.ccbmb.gardenland.core.plot.address.PlotAddress;
import ca.ccbmb.gardenland.core.plot.type.PlotType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "plot")
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(of = {"plotId"}, callSuper = false)
public class Plot {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "plot_id")
    private UUID plotId;

    @Column(name = "plot_number")
    @Generated(GenerationTime.INSERT)
    private int plotNumber;

    @Type(type = "uuid-char")
    @Column(name = "land_id")
    private UUID landId;

    @Type(type = "uuid-char")
    @Column(name = "plot_type_id")
    @Setter
    private UUID plotTypeId;


    @Column(name = "description")
    @Setter
    private String description;

    @Column(name = "size")
    @Setter
    private BigDecimal size;

    @Column(name = "price")
    @Setter
    private BigDecimal price;

    @Column(name = "size_type")
    @Setter
    @Enumerated(EnumType.STRING)
    private SizeUnitType sizeUnitType;

    @OneToOne(mappedBy = "plot", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private PlotAddress plotAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plot_type_id", insertable = false, updatable = false)
    @Setter
    private PlotType plotType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "land_id", insertable = false, updatable = false)
    private Land land;

    public static Plot newInstance(UUID landId) {
        Plot plot = new Plot();
        plot.plotId = UUID.randomUUID();
        plot.landId = landId;
        return plot;
    }
}
