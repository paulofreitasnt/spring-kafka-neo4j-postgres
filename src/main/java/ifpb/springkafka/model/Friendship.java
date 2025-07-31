package ifpb.springkafka.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User user1;
    @ManyToOne
    private User user2;
    @Column(nullable = false)
    private LocalDateTime createdAt;

}
