package ca.ccbmb.gardenland.core.user;

import ca.ccbmb.gardenland.core.land.Land;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usr")
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(of = {"userId"}, callSuper = false)
public class User {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "usr_id")
    private UUID userId;

    @Column(name = "usr_number")
    @Generated(GenerationTime.INSERT)
    private int userNumber;

    @Column(name = "name")
    @Setter
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Land> lands = new HashSet<>();

    public static User newInstance() {
        User user = new User();
        user.userId = UUID.randomUUID();
        return user;
    }
}
