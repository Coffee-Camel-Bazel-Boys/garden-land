package ca.ccbmb.gardenland.core.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userNumber;

    @Column(name = "name")
    @Setter
    private String name;

    public static User newInstance() {
        User user = new User();
        user.userId = UUID.randomUUID();
        return user;
    }
}
