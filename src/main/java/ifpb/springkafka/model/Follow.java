package ifpb.springkafka.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name = "follow",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"follower_id", "following_id"})
        }
)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name="follower_id")
    private User follower;
    @ManyToOne
    @JoinColumn(name="following_id")
    private User following;
    @Column(nullable = false)
    private LocalDateTime createdAt;

}
