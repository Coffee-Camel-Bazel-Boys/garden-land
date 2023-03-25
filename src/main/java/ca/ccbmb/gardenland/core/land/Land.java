package ca.ccbmb.gardenland.core.land;

import ca.ccbmb.gardenland.core.land.availability.LandAvailability;
import ca.ccbmb.gardenland.core.land.rule.LandRule;
import ca.ccbmb.gardenland.core.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "land", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private Set<LandRule> rules = new HashSet<>();

    @OneToMany(mappedBy = "land", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private Set<LandAvailability> availabilities = new HashSet<>();


    public static Land newInstance(UUID userId) {
        Land land = new Land();
        land.landId = UUID.randomUUID();
        land.userId = userId;
        return land;
    }
}
