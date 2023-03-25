package ca.ccbmb.gardenland.core.plot.address;

import ca.ccbmb.gardenland.core.plot.Plot;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "plot_address")
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(of = {"plotAddressId"}, callSuper = false)
public class PlotAddress {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "plot_address_id")
    private UUID plotAddressId;

    @Type(type = "uuid-char")
    @Column(name = "plot_id")
    private UUID plotId;

    @Column(name = "street_1")
    @Setter
    private String street1;

    @Column(name = "street_2")
    @Setter
    private String street2;

    @Column(name = "postal_code")
    @Setter
    private String postalCode;

    @Column(name = "city")
    @Setter
    private String city;

    @Column(name = "province")
    @Setter
    private String province;

    @Column(name = "country")
    @Setter
    private String country;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plot_id", insertable = false, updatable = false)
    private Plot plot;

    public static PlotAddress newInstance(UUID plotId) {
        PlotAddress plotAddress = new PlotAddress();
        plotAddress.plotAddressId = UUID.randomUUID();
        plotAddress.plotId = plotId;

        return plotAddress;
    }
}