package ifpb.springkafka.model;

import lombok.Data;

@Data
public class FollowRequest {

    private String emailFollower;
    private String emailFollowing;

}
