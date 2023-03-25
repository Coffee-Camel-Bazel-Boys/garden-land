package ca.ccbmb.gardenland.core.plot.picture;

import ca.ccbmb.gardenland.core.plot.Plot;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "plot_picture")
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(of = {"plotPictureId"}, callSuper = false)
public class PlotPicture {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "plot_picture_id")
    private UUID plotPictureId;

    @Column(name = "plot_picture_number")
    @Generated(GenerationTime.INSERT)
    private int plotPictureNumber;

    @Type(type = "uuid-char")
    @Column(name = "plot_id")
    private UUID plotId;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "picture")
    private byte[] picture;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plot_id", insertable = false, updatable = false)
    private Plot plot;

    public static PlotPicture newInstance(UUID plotId, String fileType, byte[] picture) {
        PlotPicture plotPicture = new PlotPicture();
        plotPicture.plotPictureId = UUID.randomUUID();
        plotPicture.plotId = plotId;
        plotPicture.fileType = fileType;
        plotPicture.picture = picture;

        return plotPicture;
    }
}

