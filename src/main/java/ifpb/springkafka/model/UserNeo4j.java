package ifpb.springkafka.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("User")
@Data
public class UserNeo4j {

    @Id
    private Long id;
    private String name;
    private String email;

    @Relationship(type="FOLLOW", direction = Relationship.Direction.INCOMING)
    private List<UserNeo4j> followers;

    @Relationship(type ="FOLLOW", direction = Relationship.Direction.OUTGOING)
    private List<UserNeo4j> following;

}
