package ca.ccbmb.gardenland.core.land;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "land")
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(of = {"landId"}, callSuper = false)
public class Land {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "land_id")
    private UUID landId;

    @Column(name = "land_number")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int landNumber;

    @Type(type = "uuid-char")
    @Column(name = "usr_id")
    private UUID userId;

    @Column(name = "description")
    @Setter
    private String description;

    public static Land newInstance(UUID userId) {
        Land land = new Land();
        land.landId = UUID.randomUUID();
        land.userId = userId;
        return land;
    }
}
