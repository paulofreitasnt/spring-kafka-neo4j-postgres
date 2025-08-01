package ifpb.springkafka.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User follower;
    @ManyToOne
    private User following;
    @Column(nullable = false)
    private LocalDateTime createdAt;

}
