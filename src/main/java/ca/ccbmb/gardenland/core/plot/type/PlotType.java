package ca.ccbmb.gardenland.core.plot.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "plot_type")
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(of = {"plotTypeId"}, callSuper = false)
public class PlotType {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "plot_type_id")
    private UUID plotTypeId;

    @Column(name = "plot_type_number")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int plotTypeNumber;

    @Column(name = "type")
    @Setter
    private String name;

    public static PlotType newInstance() {
        PlotType plotType = new PlotType();
        plotType.plotTypeId = UUID.randomUUID();
        return plotType;
    }
}
