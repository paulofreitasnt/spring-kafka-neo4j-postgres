package ifpb.springkafka.model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
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

    @Relationship(type ="FOLLOW", direction = Relationship.Direction.OUTGOING)
    private List<UserNeo4j> following;

}
