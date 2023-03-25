package ca.ccbmb.gardenland.core.land.rule;

import ca.ccbmb.gardenland.core.land.Land;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "land_rule")
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(of = {"landRuleId"}, callSuper = false)
public class LandRule {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "land_rule_id")
    private UUID landRuleId;

    @Column(name = "land_rule_number")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int landRuleNumber;

    @Type(type = "uuid-char")
    @Column(name = "land_id")
    private UUID landId;

    @Column(name = "description")
    @Setter
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "land_id", insertable = false, updatable = false)
    private Land land;

    public static LandRule newInstance(UUID landId) {
        LandRule landRule = new LandRule();
        landRule.landRuleId = UUID.randomUUID();
        landRule.landId = landId;
        return landRule;
    }
}
